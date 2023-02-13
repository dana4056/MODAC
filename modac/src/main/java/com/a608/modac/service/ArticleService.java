package com.a608.modac.service;

import com.a608.modac.model.article.ArticleRequest;
import com.a608.modac.model.article.ArticleResponse;
import com.a608.modac.model.article.LikeRequest;

public interface ArticleService {
	// 게시글 저장
	ArticleResponse.ArticleInfo createArticle(final ArticleRequest articleRequest);

	// 사용자 아이디로 게시글 목록 조회
	ArticleResponse readArticlesByUsersSeq(final Long usersSeq, final Integer offset, final Integer limit);

	// 팔로잉 게시글 목록 조회
	ArticleResponse readArticlesByFollowing(final Long usersSeq, final Integer offset, final Integer limit);

	// 게시글 번호로 게시글 조회
	ArticleResponse.ArticleInfo readArticleBySeq(final Long seq);

	void upViewCount(final Long seq);

	// 게시글 번호로 게시글 삭제
	void deleteArticleBySeq(final Long seq);

	// 게시글-유저 좋아요 관계 추가
	void createLike(final LikeRequest likeRequest);

	// 게시글-유저 좋아요 관계 삭제
	void deleteLike(final Long articlesSeq, final Long usersSeq);

	// 게시글-유저 좋아요 관계 개수 조회
	Boolean countLike(final Long articlesSeq, final Long usersSeq);
}
