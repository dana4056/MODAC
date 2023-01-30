package com.a608.modac.model.comment;

import java.time.LocalDateTime;

import com.a608.modac.model.user.User;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class CommentResponse {
	private Long seq; // 댓글 번호
	private String content; // 댓글 내용
	private String registeredTime; // 댓글 작성 시간
	private User user; // 댓글 작성자 객체

	public CommentResponse(Comment comment) {
		this.seq = comment.getSeq();
		this.content = comment.getContent();
		this.registeredTime = comment.getRegisteredTime();
		this.user = comment.getUser();
	}
}
