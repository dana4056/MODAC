package com.a608.modac.model.article;

import com.a608.modac.model.todo.Todo;
import com.a608.modac.model.user.User;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LikeRequest {
	private Long articlesSeq;
	private Long usersSeq;

	public LikeRequest(Long articlesSeq, Long usersSeq) {
		this.articlesSeq = articlesSeq;
		this.usersSeq = usersSeq;
	}

	public Like toEntity(Article article, User user){
		return Like.builder()
			.article(article)
			.user(user)
			.build();
	}
}
