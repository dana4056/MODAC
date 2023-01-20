package com.a608.modac.service;

import java.util.List;

import com.a608.modac.model.article.Article;
import com.a608.modac.model.article.ArticleRequest;
import com.a608.modac.model.todo.TodoResponse;

public interface ArticleService {
	// 게시글 등록
	void createArticle(ArticleRequest.createArticleRequest registerDto, TodoResponse todoResponse);

	// 사용자 아이디로 게시글 조회
	List<Article> readArticleByUsersSeq(Long usersSeq);

	// 게시글 번호로 게시글 조회
	Article readArticleBySeq(Long seq);

	// 게시글 번호로 게시글 삭제
	void deleteArticleBySeq(Long seq);
}
