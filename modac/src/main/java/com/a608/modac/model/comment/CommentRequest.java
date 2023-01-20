package com.a608.modac.model.comment;

import lombok.AllArgsConstructor;
import lombok.Getter;

public interface CommentRequest {
	@Getter
	@AllArgsConstructor
	public class createCommentRequest {
		private final Long articlesSeq; // 댓글을 작성할 게시글 번호
		private final Long usersSeq; // 댓글 작성자 번호
		private final String content; // 댓글 내용
	}
}
