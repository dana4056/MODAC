package com.a608.modac.service;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.a608.modac.model.article.Article;
import com.a608.modac.model.article.ArticleRequest;
import com.a608.modac.model.article.ArticleResponse;
import com.a608.modac.model.article.Like;
import com.a608.modac.model.article.LikeRequest;
import com.a608.modac.model.follow.Follow;
import com.a608.modac.model.todo.Todo;
import com.a608.modac.model.user.User;
import com.a608.modac.repository.ArticleRepository;
import com.a608.modac.repository.FollowRepository;
import com.a608.modac.repository.LikeRepository;
import com.a608.modac.repository.TodoRepository;
import com.a608.modac.repository.UserRepository;

@Service
public class ArticleServiceImpl implements ArticleService {

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

	public ArticleServiceImpl(ArticleRepository articleRepository, TodoRepository todoRepository, LikeRepository likeRepository,
		UserRepository userRepository, FollowRepository followRepository) {
		this.articleRepository = articleRepository;
		this.todoRepository = todoRepository;
		this.likeRepository = likeRepository;
		this.userRepository = userRepository;
		this.followRepository = followRepository;
	}

	// 게시글 저장
	@Override
	public ArticleResponse.ArticleInfo createArticle(final ArticleRequest articleRequest) {
		Todo todo = todoRepository.findById(articleRequest.getTodosSeq())
			.orElseThrow(() -> new NoSuchElementException("NoTodo")); // todosSeq를 이용하여 todo 호출
		Article save = articleRepository.save(articleRequest.toEntity(todo));// Article 빌드 후 저장
		System.out.println("+++++++++++++++++++++" + save);

		// 게시글 등록 시 30포인트 적립
		Integer point = 30;
		LocalDateTime nowLDT = LocalDateTime.now(); // 현재 시간
		DayOfWeek dayOfWeek = nowLDT.getDayOfWeek(); // 현재 요일
		if (nowLDT.getHour() < 6) { // 현재 시간이 오전 12:00 ~ 오전 5:59일 경우
			dayOfWeek = dayOfWeek.minus(1); // 전날로 취급함
		}
		if (dayOfWeek == DayOfWeek.SUNDAY || dayOfWeek == DayOfWeek.SATURDAY) {
			point += 30; // 현재 요일을 검사해서 토, 일요일이면 30포인트 더 지급
		}
		User user = userRepository.findById(articleRequest.getUsersSeq())
			.orElseThrow(() -> new NoSuchElementException("NoUser"));
		user.updatePoint(point);
		userRepository.save(user);

		return new ArticleResponse.ArticleInfo(save);
	}

	// 사용자 아이디로 게시글 목록 조회
	@Override
	public ArticleResponse readArticlesByUsersSeq(final Long usersSeq, final Integer offset, final Integer limit) {
		List<Article> findArticles = articleRepository.findByUser_Seq(usersSeq);
		// 최신 날짜를 우선으로 정렬
		final DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		Collections.sort(findArticles, (o1, o2) -> {
			final LocalDateTime ldt1 = LocalDateTime.parse(o1.getRegisteredTime(), pattern);
			final LocalDateTime ldt2 = LocalDateTime.parse(o2.getRegisteredTime(), pattern);
			return (ldt1.isBefore(ldt2)) ? 1 : -1;
		});
		final Integer totalArticleCnt = findArticles.size(); // 총 게시글 수
		final Integer totalPageCnt = ((totalArticleCnt - 1) / limit) + 1; // 총 페이지 수
		final Integer st = (offset - 1) * limit; // 해당 페이지 시작 게시글 인덱스
		final Integer ed = Math.min(offset * limit, totalArticleCnt); // 해당 페이지 마지막 게시글 인덱스

		return new ArticleResponse(findArticles.subList(st, ed)
			.stream()
			.map(ArticleResponse.ArticleInfo::new)
			.collect(Collectors.toList()),
			totalArticleCnt, totalPageCnt);
	}

	// 팔로잉 게시글 목록 조회
	@Override
	public ArticleResponse readArticlesByFollowing(final Long usersSeq, final Integer offset, final Integer limit) {
		List<Follow> followingList = followRepository.findAllByFromUser_Seq(usersSeq);
		// 팔로잉하는 모든 사람의 게시글 가져오기
		List<Article> findArticles = new ArrayList<>();
		for (Follow following : followingList) {
			Long userSeq = following.getToUser().getSeq();
			findArticles.addAll(articleRepository.findByUser_Seq(userSeq));
		}
		// 최신 날짜를 우선으로 정렬
		final DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		Collections.sort(findArticles, (o1, o2) -> {
			final LocalDateTime ldt1 = LocalDateTime.parse(o1.getRegisteredTime(), pattern);
			final LocalDateTime ldt2 = LocalDateTime.parse(o2.getRegisteredTime(), pattern);
			return (ldt1.isBefore(ldt2)) ? 1 : -1;
		});
		final Integer totalArticleCnt = findArticles.size(); // 총 게시글 수
		final Integer totalPageCnt = ((totalArticleCnt - 1) / limit) + 1; // 총 페이지 수
		final Integer st = (offset - 1) * limit; // 해당 페이지 시작 게시글 인덱스
		final Integer ed = Math.min(offset * limit, totalArticleCnt); // 해당 페이지 마지막 게시글 인덱스

		return new ArticleResponse(findArticles.subList(st, ed)
			.stream()
			.map(ArticleResponse.ArticleInfo::new)
			.collect(Collectors.toList()),
			totalArticleCnt, totalPageCnt);
	}

	// 게시글 번호로 게시글 조회
	@Override
	public ArticleResponse.ArticleInfo readArticleBySeq(final Long seq) {
		Article article = articleRepository.findById(seq).orElseThrow(() -> new NoSuchElementException("NoArticle"));
		return new ArticleResponse.ArticleInfo(article);
	}

	// 게시글 조회수 업
	@Override
	public void upViewCount(final Long seq) {
		Article article = articleRepository.findById(seq).orElseThrow(() -> new NoSuchElementException("NoArticle"));
		article.upViewCount();
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
		if(likeRepository.findByArticleAndUser(article, user) != null){
			return;
		}

		// 좋아요 테이블에 관계 추가
		likeRepository.save(likeRequest.toEntity(article, user));

		// 좋아요수 1증가 후 저장
		article.updateLikeCount(1);
		articleRepository.save(article);

	}

	// 게시글-유저 좋아요 관계 삭제
	@Override
	public void deleteLike(final Long articlesSeq, final Long usersSeq) {
		// Validation
		Article article = articleRepository.findById(articlesSeq).orElseThrow(() -> new NoSuchElementException("NoArticle"));
		User user = userRepository.findById(usersSeq).orElseThrow(() -> new NoSuchElementException("NoUser"));
		Like like = likeRepository.findByArticleAndUser(article, user);
		if(like == null){
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
		Article article = articleRepository.findById(articlesSeq).orElseThrow(() -> new NoSuchElementException("NoArticle"));
		User user = userRepository.findById(usersSeq).orElseThrow(() -> new NoSuchElementException("NoUser"));

		// 존재하면 true, 존재하지 않으면 false 반환
		return likeRepository.findByArticleAndUser(article, user) != null ? true : false;
	}
}
