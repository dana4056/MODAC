package com.a608.modac.service;

import java.util.List;

import com.a608.modac.model.comment.CommentRequest;
import com.a608.modac.model.comment.CommentResponse;

public interface CommentService {
	// 댓글 작성
	void createComment(final CommentRequest commentRequest);

	// 댓글 삭제
	void deleteComment(final Long seq);

	// 댓글 목록조회
	List<CommentResponse> readCommentByArticlesSeq(final Long articlesSeq);
}
