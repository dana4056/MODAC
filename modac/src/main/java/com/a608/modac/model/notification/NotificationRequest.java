package com.a608.modac.model.notification;

import java.time.LocalDateTime;

import com.a608.modac.model.article.Article;
import com.a608.modac.model.user.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class NotificationRequest {
	private Long articlesSeq;
	private Long fromUsersSeq;
	private Long toUsersSeq;
	private String type;

	public Notification toEntity(Article article, User fromUser, User toUser, String type) {
		return Notification.builder()
			.registeredTime(LocalDateTime.now())
			.article(article)
			.fromUser(fromUser)
			.toUser(toUser)
			.isRead((byte)1)
			.type(type)
			.build();
	}
}