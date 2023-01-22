package com.a608.modac.model.comment;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class CommentRequest {
	private Long articlesSeq;
	private Long usersSeq;
	private String content;

	public Comment toEntity() {
		return Comment.builder()
			.articlesSeq(articlesSeq)
			.usersSeq(usersSeq)
			.content(content)
			.build();
	}

}
