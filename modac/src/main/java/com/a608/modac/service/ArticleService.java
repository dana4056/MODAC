package com.a608.modac.service;

import java.util.List;

import com.a608.modac.model.article.Article;
import com.a608.modac.model.article.ArticleRequest;
import com.a608.modac.model.article.ArticleResponse;
import com.a608.modac.model.todo.TodoResponse;

public interface ArticleService {
	// 게시글 저장
	ArticleResponse createArticle(final ArticleRequest articleRequest);

	// 사용자 아이디로 게시글 조회
	List<ArticleResponse> readArticleByUsersSeq(final Long usersSeq);

	// 게시글 번호로 게시글 조회
	ArticleResponse readArticleBySeq(final Long seq);

	// 게시글 번호로 게시글 삭제
	void deleteArticleBySeq(final Long seq);

	// 게시글-유저 좋아요 관계 추가
	void createLike(final Long articlesSeq, final Long usersSeq);

	// 게시글-유저 좋아요 관계 삭제
	void deleteLike(final Long articlesSeq, final Long usersSeq);

	// 게시글-유저 좋아요 관계 개수 조회
	Long countLike(final Long articlesSeq, final Long usersSeq);
}
