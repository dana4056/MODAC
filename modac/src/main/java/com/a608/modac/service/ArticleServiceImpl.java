package com.a608.modac.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.a608.modac.model.article.Article;
import com.a608.modac.model.article.ArticleRepository;
import com.a608.modac.model.article.ArticleRequest;
import com.a608.modac.model.article.ArticleResponse;
import com.a608.modac.model.todo.Todo;
import com.a608.modac.model.todo.TodoRepository;

@Service
public class ArticleServiceImpl implements ArticleService {

	@Resource(name = "articleRepository")
	private final ArticleRepository articleRepository;

	@Resource(name = "todoRepository")
	private final TodoRepository todoRepository;

	public ArticleServiceImpl(ArticleRepository articleRepository, TodoRepository todoRepository) {
		this.articleRepository = articleRepository;
		this.todoRepository = todoRepository;
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
		final List<Article> findArticles = articleRepository.findByUsersSeq(usersSeq);
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
}
