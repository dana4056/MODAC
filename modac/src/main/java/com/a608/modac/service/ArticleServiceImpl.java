package com.a608.modac.service;

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
			.orElseThrow(NoSuchElementException::new); // todosSeq를 이용하여 todo 호출
		Article save = articleRepository.save(articleRequest.toEntity(todo));// Article 빌드 후 저장
		System.out.println("+++++++++++++++++++++" + save);
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
		return new ArticleResponse.ArticleInfo(
			articleRepository.findById(seq).orElseThrow(NoSuchElementException::new));
	}

	// 게시글 조회수 업
	@Override
	public void upViewCount(final Long seq) {
		Article article = articleRepository.findById(seq).orElseThrow(NoSuchElementException::new);
		article.upViewCount();
		articleRepository.save(article);
	}

	// 게시글 번호로 게시글 삭제
	@Override
	public void deleteArticleBySeq(final Long seq) {
		articleRepository.deleteById(seq);
	}


	// 게시글-유저 좋아요 (좋아요 테이블에 관계 추가하고 게시글 좋아요수 올려서 업데이트)
	// 해당 유저가 해당 게시물을 처음 좋아요 한다는 전제에 호출됨 -> 이미 좋아요 했는지 아닌지 확인할 필요 X
	@Override
	public void createLike(final LikeRequest likeRequest) {

		Long articlesSeq = likeRequest.getArticlesSeq();
		Long usersSeq = likeRequest.getUsersSeq();

		Article article = articleRepository.findById(articlesSeq).orElseThrow(NoSuchElementException::new);
		User user = userRepository.findById(usersSeq).orElseThrow(NoSuchElementException::new);

		// 좋아요수 1증가 후 저장
		article.updateLikeCount(1);
		articleRepository.save(article);

		// 좋아요 테이블에 관계 추가
		likeRepository.save(likeRequest.toEntity(article, user));
	}

	// 게시글-유저 좋아요 관계 삭제
	@Override
	public void deleteLike(final LikeRequest likeRequest) {

		Long articlesSeq = likeRequest.getArticlesSeq();
		Long usersSeq = likeRequest.getUsersSeq();

		Article article = articleRepository.findById(articlesSeq).orElseThrow(NoSuchElementException::new);

		// 좋아요수 1 감소 후 저장
		article.updateLikeCount(-1);
		articleRepository.save(article);

		// 좋아요 테이블에서 관계 삭제
		Like like = likeRepository.findLikeByArticle_SeqAndUser_Seq(articlesSeq, usersSeq);
		likeRepository.delete(like);
	}

	// 게시글-유저 좋아요 관계 개수 조회 (좋아요 했는지 안했는지)
	@Override
	public Boolean countLike(final LikeRequest likeRequest) {

		Like like = likeRepository.findLikeByArticle_SeqAndUser_Seq(likeRequest.getArticlesSeq(), likeRequest.getUsersSeq());
		return like != null ? true : false;
	}
}
