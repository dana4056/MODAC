package com.a608.modac.service;

import java.util.List;

import com.a608.modac.model.Article;
import com.a608.modac.model.ArticleRequest;


public interface ArticleService {
	// 게시글 등록
	void registArticle(ArticleRequest.Register registerDto);

	// 사용자 아이디로 게시글 조회
	List<Article> selectArticleByUsersSeq(Long usersSeq);

	// 게시글 번호로 게시글 조회
	Article selectArticleBySeq(Long seq);

	// 게시글 번호로 게시글 삭제
	void deleteArticleBySeq(Long seq);
}
