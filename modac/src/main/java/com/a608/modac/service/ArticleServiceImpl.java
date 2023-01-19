package com.a608.modac.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.a608.modac.model.Article;
import com.a608.modac.model.ArticleRepository;
import com.a608.modac.model.ArticleRequest;

@Service
public class ArticleServiceImpl implements ArticleService {

	@Resource(name="articleRepository")
	private ArticleRepository articleRepository;

	@Override
	public void registArticle(ArticleRequest.Register registerDto) {

		// ================================
		// TodoRepository를 import해서 todosSeq로 검색
		// 검색 결과를 Todo 객체로 저장하여 값들을 article로 가져오기
		// Todo todoResponse = new Todo();
		// todoResponse.
		// ================================


		// Article article = new Article(어쩌고저쩌고);
		// articleRepository.save(article);

	}

	@Override
	public List<Article> selectArticleByUsersSeq(Long usersSeq) {
		return articleRepository.findByUsersSeq(usersSeq);
	}

	@Override
	public Article selectArticleBySeq(Long seq) {
		Optional<Article> article = articleRepository.findById(seq);
		return article.orElseThrow(() -> new NoSuchElementException("Article not found."));
	}

	@Override
	public void deleteArticleBySeq(Long seq) {
		articleRepository.deleteById(seq);
	}
}
