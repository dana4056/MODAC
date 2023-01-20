package com.a608.modac.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.a608.modac.model.article.Article;
import com.a608.modac.model.article.ArticleRepository;
import com.a608.modac.model.article.ArticleRequest;
import com.a608.modac.model.todo.TodoRepository;
import com.a608.modac.model.todo.TodoResponse;

@Service
public class ArticleServiceImpl implements ArticleService {

	@Resource(name="articleRepository")
	private ArticleRepository articleRepository;

	@Resource(name="todoRepository")
	private TodoRepository todoRepository;



	@Override
	public void createArticle(ArticleRequest.createArticleRequest registerDto, TodoResponse todoResponse) {
		// Article 객체에 들어갈 요소들 정리
		String title = todoResponse.getTitle();
		String filepath = registerDto.getFilepath();
		String registeredTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		int publicType = registerDto.getPublicType();
		String totalSecond = todoResponse.getTotalSecond();
		Long usersSeq = registerDto.getUsersSeq();
		Long categoriesSeq = todoResponse.getCategoriesSeq();

		// Article 객체 생성
		Article article = new Article(null, title, filepath, registeredTime, publicType,
			 0, 0, 0, totalSecond, usersSeq, categoriesSeq);

		// Article 객체 저장
		articleRepository.save(article);
	}

	@Override
	public List<Article> readArticleByUsersSeq(Long usersSeq) {
		return articleRepository.findByUsersSeq(usersSeq);
	}

	@Override
	public Article readArticleBySeq(Long seq) {
		Optional<Article> article = articleRepository.findById(seq);
		return article.orElseThrow(() -> new NoSuchElementException("Article not found."));
	}

	@Override
	public void deleteArticleBySeq(Long seq) {
		articleRepository.deleteById(seq);
	}
}
