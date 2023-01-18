package com.a608.modac.service;

import java.util.List;

import com.a608.modac.model.Article;

public interface ArticleService {
	// 게시글 등록
	void registArticle(long usersSeq, long todosSeq, int publicType, String filepath);

	// 사용자 아이디로 게시글 조회
	List<Article> selectArticleByUsersId(String usersId);

	// 게시글 번호로 게시글 조회
	Article selectArticleBySeq(long seq);

	// 게시글 번호로 게시글 삭제
	void deleteArticleBySeq(long seq);
}
