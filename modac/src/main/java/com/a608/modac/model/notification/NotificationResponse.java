package com.a608.modac.model.notification;

import java.time.LocalDateTime;

import com.a608.modac.model.article.Article;
import com.a608.modac.model.user.User;
import com.a608.modac.repository.UserRepository;

import lombok.Getter;

@Getter
public class NotificationResponse {
	private Long seq;
	private LocalDateTime registeredTime;
	private Article article;
	private User fromUser;
	private User toUser;
	private Byte isRead;
	private String type;

	public NotificationResponse(Notification notification) {
		this.seq = notification.getSeq();
		this.registeredTime = notification.getRegisteredTime();
		this.article = notification.getArticle();
		this.fromUser = notification.getFromUser();
		this.toUser = notification.getToUser();
		this.isRead = notification.getIsRead();
		this.type = notification.getType();
	}
}