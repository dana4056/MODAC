package com.a608.modac.model.comment;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class CommentResponse {
	@Getter
	@AllArgsConstructor
	public class readCommentResponse {
		private final Long seq; // 댓글 번호
		private final String content; // 댓글 내용
		private final LocalDateTime registeredTime; // 댓글 작성 시간
		private final Long usersSeq; // 댓글 작성자 번호
	}
}
