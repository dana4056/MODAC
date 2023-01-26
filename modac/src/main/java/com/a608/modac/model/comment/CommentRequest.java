package com.a608.modac.model.comment;

import com.a608.modac.model.article.Article;
import com.a608.modac.model.user.User;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class CommentRequest {
	private Long articlesSeq;
	private Long usersSeq;
	private String content;

	public Comment toEntity(Article article, User user) {
		return Comment.builder()
			.article(article)
			.user(user)
			.content(content)
			.build();
	}

}
