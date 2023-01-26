package com.a608.modac.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.a608.modac.model.article.Article;
import com.a608.modac.model.user.User;
import com.a608.modac.repository.ArticleRepository;
import com.a608.modac.model.article.ArticleRequest;
import com.a608.modac.model.article.ArticleResponse;
import com.a608.modac.model.article.Like;
import com.a608.modac.model.article.LikeId;
import com.a608.modac.repository.LikeRepository;
import com.a608.modac.model.todo.Todo;
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

	public ArticleServiceImpl(ArticleRepository articleRepository, TodoRepository todoRepository, LikeRepository likeRepository,
		UserRepository userRepository) {
		this.articleRepository = articleRepository;
		this.todoRepository = todoRepository;
		this.likeRepository = likeRepository;
		this.userRepository = userRepository;
	}

	// 게시글 저장
	@Override
	public void createArticle(final ArticleRequest articleRequest) {
		Todo todo = todoRepository.findById(articleRequest.getTodosSeq())
			.orElseThrow(NoSuchElementException::new); // todosSeq를 이용하여 todo 호출
		articleRepository.save(articleRequest.toEntity(todo)); // Article 빌드 후 저장
	}

	// 사용자 아이디로 게시글 조회
	@Override
	public List<ArticleResponse> readArticleByUsersSeq(final Long usersSeq) {
		final List<Article> findArticles = articleRepository.findByUser_Seq(usersSeq);
		return findArticles.stream().map(ArticleResponse::new).collect(Collectors.toList());
	}

	// 게시글 번호로 게시글 조회
	@Override
	public ArticleResponse readArticleBySeq(final Long seq) {
		return new ArticleResponse(articleRepository.findById(seq).orElseThrow(NoSuchElementException::new));
	}

	// 게시글 번호로 게시글 삭제
	@Override
	public void deleteArticleBySeq(final Long seq) {
		articleRepository.deleteById(seq);
	}


	// 게시글-유저 좋아요 관계 추가
	@Override
	public void createLike(final Long articlesSeq, final Long usersSeq) {

		Article article = articleRepository.findById(articlesSeq).orElseThrow(NoSuchElementException::new);
		User user = userRepository.findById(usersSeq).orElseThrow(NoSuchElementException::new);

		likeRepository.save(Like.builder().article(article).user(user).build());
	}

	// 게시글-유저 좋아요 관계 삭제
	@Override
	public void deleteLike(final Long articlesSeq, final Long usersSeq) {

		Like like = likeRepository.findLikeByArticle_SeqAndUser_Seq(articlesSeq, usersSeq);
		likeRepository.delete(like);
	}

	// 게시글-유저 좋아요 관계 개수 조회 (좋아요 했는지 안했는지)
	@Override
	public Long countLike(final Long articlesSeq, final Long usersSeq) {

		Like like = likeRepository.findLikeByArticle_SeqAndUser_Seq(articlesSeq, usersSeq);

		return likeRepository.countBySeq(like.getSeq());
	}
}
