package com.a608.modac.service;

import com.a608.modac.repository.ArticleQueryRepository;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.a608.modac.model.article.Article;
import com.a608.modac.model.article.ArticleRequest;
import com.a608.modac.model.article.ArticleResponse;
import com.a608.modac.model.article.Like;
import com.a608.modac.model.article.LikeRequest;
import com.a608.modac.model.article.StatisticsResponse;
import com.a608.modac.model.follow.Follow;
import com.a608.modac.model.notification.Notification;
import com.a608.modac.model.todo.Todo;
import com.a608.modac.model.user.User;
import com.a608.modac.repository.ArticleRepository;
import com.a608.modac.repository.FollowRepository;
import com.a608.modac.repository.LikeRepository;
import com.a608.modac.repository.NotificationRepository;
import com.a608.modac.repository.TodoRepository;
import com.a608.modac.repository.UserRepository;
import org.springframework.transaction.annotation.Transactional;

import static com.a608.modac.model.article.ArticleResponse.*;

@Service
public class ArticleServiceImpl implements ArticleService {

    public static final String DELIMITER = "_";
    public static final String COOKIE_NAME_PREFIX = "CheckedArticle";
    public static final String NO_ARTICLE = "NoArticle";
    @Resource(name = "articleRepository")
    private final ArticleRepository articleRepository;

    @Resource(name = "todoRepository")
    private final TodoRepository todoRepository;

    @Resource(name = "likeRepository")
    private final LikeRepository likeRepository;

    @Resource(name = "userRepository")
    private final UserRepository userRepository;

    @Resource(name = "followRepository")
    private final FollowRepository followRepository;

    @Resource(name = "notificationRepository")
    private final NotificationRepository notificationRepository;

    @Resource(name = "articleQueryRepository")
    private final ArticleQueryRepository articleQueryRepository;

    @Autowired
    private S3Service s3Service;

    @Autowired
    private RedisService redisService;

    public ArticleServiceImpl(ArticleRepository articleRepository, TodoRepository todoRepository,
                              LikeRepository likeRepository,
                              UserRepository userRepository, FollowRepository followRepository,
                              NotificationRepository notificationRepository, final ArticleQueryRepository articleQueryRepository) {
        this.articleRepository = articleRepository;
        this.todoRepository = todoRepository;
        this.likeRepository = likeRepository;
        this.userRepository = userRepository;
        this.followRepository = followRepository;
        this.notificationRepository = notificationRepository;
        this.articleQueryRepository = articleQueryRepository;
    }

    // 게시글 저장
    @Override
    public ArticleInfoResponse createArticle(final ArticleRequest articleRequest) {
        String objectKey = articleRequest.getContent();

        // 게시글 내용 s3에 저장
//		objectKey = s3Service.save(s3Service.createMultipartFile(articleRequest.getContent()));
        System.out.println(objectKey);

        // Todo 정보를 불러와서 Article 객체 빌드
        Todo todo = todoRepository.findById(articleRequest.getTodosSeq())
                .orElseThrow(() -> new NoSuchElementException("NoTodo")); // todosSeq를 이용하여 todo 호출
        Article article = articleRequest.toEntity(todo, objectKey);
        Article save = articleRepository.save(article);// Article 빌드 후 저장
        System.out.println("+++++++++++++++++++++" + save);

        // 게시글 등록 시 30포인트 적립
        Integer point = 30;
        LocalDateTime nowLDT = article.getRegisteredTime(); // 현재 시간
        DayOfWeek dayOfWeek = nowLDT.getDayOfWeek(); // 현재 요일
        if (nowLDT.getHour() < 6) { // 현재 시간이 오전 12:00 ~ 오전 5:59일 경우
            dayOfWeek = dayOfWeek.minus(1); // 전날로 취급함
        }
        if (dayOfWeek == DayOfWeek.SUNDAY || dayOfWeek == DayOfWeek.SATURDAY) {
            point += 30; // 현재 요일을 검사해서 토, 일요일이면 30포인트 더 지급
        }
        User user = userRepository.findById(articleRequest.getUsersSeq())
                .orElseThrow(() -> new NoSuchElementException("NoUser"));
//		user.updatePoint(point);
        userRepository.save(user);

        return new ArticleInfoResponse(save);
    }

    // 사용자 아이디로 게시글 목록 조회
    @Override
    public Page<ArticleInfoResponse> selectArticlesByUsersSeq(final Long usersSeq, final Pageable pageable, final Long articleSeq, final Integer size) {
//    public Page<ArticleInfoResponse> selectArticlesByUsersSeq(final Long usersSeq, final Pageable pageable, final Integer articleSeq, final Integer size) {
        User myUser = userRepository.findById(usersSeq).orElseThrow(() -> new NoSuchElementException("NoUser"));
//        Pageable pageRequest = PageRequest.of(articleSeq, size, Sort.Direction.DESC, "seq");
//        return articleRepository.findArticlesByUser(myUser, pageRequest).map(ArticleInfoResponse::new); // 4.21초

        return articleQueryRepository.searchArticleByUser(usersSeq, articleSeq, pageable); // 2.7초
    }

    // 팔로잉 게시글 목록 조회
    @Override
    public ArticleResponse readArticlesByFollowing(final Long usersSeq, final Integer offset, final Integer limit) {
        User myUser = userRepository.findById(usersSeq).orElseThrow(() -> new NoSuchElementException("NoUser"));
        List<Follow> followingList = followRepository.findAllByFromUser_Seq(usersSeq);
        // 팔로잉하는 모든 사람의 게시글 가져오기
        List<Article> findArticles = new ArrayList<>();

        for (Follow following : followingList) {
            User user = userRepository.findById(following.getToUser().getSeq())
                    .orElseThrow(() -> new NoSuchElementException("NoUser"));
            findArticles.addAll(articleRepository.findAllByUserAndPublicType(user, (byte) 1));
        }

        // 내 게시글 전부 가져오기
        findArticles.addAll(articleRepository.findByUser(myUser));
        // 최신 날짜를 우선으로 정렬
        Collections.sort(findArticles, (o1, o2) -> o1.getRegisteredTime().isBefore(o2.getRegisteredTime()) ? 1 : -1);

        final Integer totalArticleCnt = findArticles.size(); // 총 게시글 수
        final Integer totalPageCnt = ((totalArticleCnt - 1) / limit) + 1; // 총 페이지 수
        final Integer st = (offset - 1) * limit; // 해당 페이지 시작 게시글 인덱스
        final Integer ed = Math.min(offset * limit, totalArticleCnt); // 해당 페이지 마지막 게시글 인덱스

        return new ArticleResponse(findArticles.subList(st, ed)
                .stream()
                .map(ArticleInfoResponse::new)
                .collect(Collectors.toList()),
                totalArticleCnt, totalPageCnt);
    }

    // 게시글 번호로 게시글 조회
    @Override
    @Transactional
    public ArticleInfoResponse readArticleBySeq(final Long articleId, final HttpServletRequest request,
                                                final HttpServletResponse response) {
        Article article = articleRepository.findById(articleId).orElseThrow(() -> new NoSuchElementException(NO_ARTICLE));
        Cookie[] cookies = request.getCookies();

        if (cookies == null || Arrays.stream(cookies).noneMatch(cookie -> cookie.getName().equals(COOKIE_NAME_PREFIX + articleId))) {
            Cookie cookie = new Cookie(COOKIE_NAME_PREFIX + articleId, String.valueOf(articleId));
            cookie.setMaxAge((int) LocalDateTime.now().until(LocalDate.now().atTime(LocalTime.MAX), ChronoUnit.SECONDS));
            cookie.setHttpOnly(true);

            response.addCookie(cookie);
            article.upViewCount();
        }

        return new ArticleInfoResponse(article);
    }

    // 게시글 조회수 업
    @Override
    public void upViewCount(final Long seq, final Long usersSeq) {
        Article article = articleRepository.findById(seq).orElseThrow(() -> new NoSuchElementException("NoArticle"));
        Optional<String> byId = redisService.findById(usersSeq);

        if (byId.isPresent()) {
            String[] split = byId.get().split(DELIMITER);
            System.out.println(Arrays.toString(split));
            List<String> readArticles = Arrays.stream(split).collect(Collectors.toList());

            if (!readArticles.contains(String.valueOf(seq))) {
                redisService.saveReadArticle(usersSeq, seq);
                article.upViewCount();
            }
        } else {
            redisService.saveReadArticle(usersSeq, seq);
            article.upViewCount();
        }

        articleRepository.save(article);
    }

    // 게시글 번호로 게시글 삭제
    @Override
    public void deleteArticleBySeq(final Long seq) {
        Article article = articleRepository.findById(seq).orElseThrow(() -> new NoSuchElementException("NoArticle"));
        articleRepository.deleteById(seq);
    }

    // 게시글-유저 좋아요 (좋아요 테이블에 관계 추가하고 게시글 좋아요수 올려서 업데이트)
    // 해당 유저가 해당 게시물을 처음 좋아요 한다는 전제에 호출됨 -> 이미 좋아요 했는지 아닌지 확인할 필요 X
    // 하지만 혹시 몰라서 검증 코드를 나중에 추가했습니다.
    @Override
    public void createLike(final LikeRequest likeRequest) {
        // Validation
        Article article = articleRepository.findById(likeRequest.getArticlesSeq()).orElseThrow(() -> new NoSuchElementException("NoArticle"));
        User user = userRepository.findById(likeRequest.getUsersSeq()).orElseThrow(() -> new NoSuchElementException("NoUser"));
        Like like = likeRepository.findByArticleAndUser(article, user);
        if (likeRepository.findByArticleAndUser(article, user) != null) {
            return;
        }

        // 좋아요 테이블에 관계 추가
        likeRepository.save(likeRequest.toEntity(article, user));

        // 좋아요수 1증가 후 저장
        article.updateLikeCount(1);
        articleRepository.save(article);

        // 게시글 주인과 좋아요 누른 사용자가 다르면 알림 보내기
        if (!article.getUser().equals(user)) {
            notificationRepository.save(Notification.builder()
                    .registeredTime(LocalDateTime.now())
                    .article(article)
                    .fromUser(user)
                    .toUser(article.getUser())
                    .isRead((byte) 1)
                    .type("like")
                    .build());
        }
    }

    // 게시글-유저 좋아요 관계 삭제
    @Override
    public void deleteLike(final Long articlesSeq, final Long usersSeq) {
        // Validation
        Article article = articleRepository.findById(articlesSeq).orElseThrow(() -> new NoSuchElementException("NoArticle"));
        User user = userRepository.findById(usersSeq).orElseThrow(() -> new NoSuchElementException("NoUser"));
        Like like = likeRepository.findByArticleAndUser(article, user);

        if (like == null) {
            throw new NoSuchElementException("NoLike");
        }

        // 좋아요 테이블에서 관계 삭제
        likeRepository.delete(like);

        // 좋아요수 1 감소 후 저장
        article.updateLikeCount(-1);
        articleRepository.save(article);
    }

    // 게시글-유저 좋아요 관계 개수 조회 (좋아요 했는지 안했는지)
    @Override
    public Boolean countLike(final Long articlesSeq, final Long usersSeq) {
        // Validation
        Article article = articleRepository.findById(articlesSeq)
                .orElseThrow(() -> new NoSuchElementException("NoArticle"));
        User user = userRepository.findById(usersSeq).orElseThrow(() -> new NoSuchElementException("NoUser"));

        // 존재하면 true, 존재하지 않으면 false 반환
        return likeRepository.findByArticleAndUser(article, user) != null ? true : false;
    }

    @Override
    public StatisticsResponse findStats(final Long usersSeq) {
        // Validation
        User user = userRepository.findById(usersSeq).orElseThrow(() -> new NoSuchElementException("NoUser"));

        // 0. 최근 일주일 간 사용자가 작성한 게시글 리스트 가져오기
        LocalDateTime nowDateTime = LocalDateTime.now();
        if (nowDateTime.getHour() < 6) { // 6시 이전이면 아직 날짜가 안 넘어갔으므로 하루 더 전까지
            nowDateTime.minusDays(1);
        }
        LocalDateTime firstDateTime = nowDateTime.minusDays(6);
        firstDateTime = firstDateTime.withHour(6).withMinute(0).withSecond(0).withNano(0); // 날짜가 넘어가는 기준 시간
        List<Article> articles = articleRepository.findAllByUserAndRegisteredTimeGreaterThanEqual(user, firstDateTime); // 최근 일주일 게시글 리스트
        Collections.sort(articles, (o1, o2) -> o1.getRegisteredTime().isBefore(o2.getRegisteredTime()) ? 1 : -1);

        // 1. 요일별 사용자의 게시글 수
        List<StatisticsResponse.CountByDayOfWeek> daysOfWeekList = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            LocalDateTime startDateTime = firstDateTime.plusDays(i);
            String dayOfWeek = startDateTime.getDayOfWeek().toString(); // 요일(영어)
            String dateInfo = startDateTime.getMonth().getValue() + "/" + startDateTime.getDayOfMonth(); // 날짜정보 (ex: 2/17(토) )
            switch (startDateTime.getDayOfWeek().getValue()) {
                case 1:
                    dateInfo += "(월)";
                    break;
                case 2:
                    dateInfo += "(화)";
                    break;
                case 3:
                    dateInfo += "(수)";
                    break;
                case 4:
                    dateInfo += "(목)";
                    break;
                case 5:
                    dateInfo += "(금)";
                    break;
                case 6:
                    dateInfo += "(토)";
                    break;
                case 7:
                    dateInfo += "(일)";
                    break;
            }
            Integer countArticles = 0; // 게시글의 개수
            daysOfWeekList.add(new StatisticsResponse.CountByDayOfWeek(dayOfWeek, dateInfo, countArticles));
        }
        // 찾은 게시글들의 날짜에 맞게 카운트 증가
        for (Article article : articles) {
            LocalDate targetDate = article.getRegisteredTime().toLocalDate();
            if (article.getRegisteredTime().getHour() < 6) {
                targetDate.minusDays(1);
            }
            int diffDays = Period.between(firstDateTime.toLocalDate(), targetDate).getDays();
            daysOfWeekList.get(diffDays).plusCount();
        }

        // 2. 카테고리별 사용자의 게시글 수
        List<StatisticsResponse.CountByCategory> categoriesList = new ArrayList<>();
        categoriesList.add(new StatisticsResponse.CountByCategory("알고리즘", 0));
        categoriesList.add(new StatisticsResponse.CountByCategory("개발", 0));
        categoriesList.add(new StatisticsResponse.CountByCategory("CS", 0));
        categoriesList.add(new StatisticsResponse.CountByCategory("면접", 0));
        categoriesList.add(new StatisticsResponse.CountByCategory("공통", 0));
        // 찾은 게시글들의 카테고리에 맞게 카운트 증가
        for (Article article : articles) {
            switch (article.getCategory().getName()) {
                case "알고리즘":
                    categoriesList.get(0).plusCount();
                    break;
                case "개발":
                    categoriesList.get(1).plusCount();
                    break;
                case "CS":
                    categoriesList.get(2).plusCount();
                    break;
                case "면접":
                    categoriesList.get(3).plusCount();
                    break;
                case "공통":
                    categoriesList.get(4).plusCount();
                    break;
            }
        }

        // 정리된 통계 반환
        return new StatisticsResponse(daysOfWeekList, categoriesList);
    }

}
