package com.a608.modac.service;

import java.util.List;

import com.a608.modac.model.article.Article;
import com.a608.modac.model.article.ArticleRequest;
import com.a608.modac.model.article.ArticleResponse;
import com.a608.modac.model.todo.TodoResponse;

public interface ArticleService {
	// 게시글 저장
	void createArticle(ArticleRequest articleRequest);

	// 사용자 아이디로 게시글 조회
	List<ArticleResponse> readArticleByUsersSeq(Long usersSeq);

	// 게시글 번호로 게시글 조회
	ArticleResponse readArticleBySeq(Long seq);

	// 게시글 번호로 게시글 삭제
	void deleteArticleBySeq(Long seq);
}
