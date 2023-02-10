package com.a608.modac.model.notification;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;

import com.a608.modac.model.article.Article;
import com.a608.modac.model.user.User;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Entity
@ToString
@DynamicInsert
@Table(name = "notifications")
public class Notification {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long seq;

	@Column(name = "registered_time")
	private LocalDateTime registeredTime;

	@ManyToOne
	@JoinColumn(name = "articles_seq")
	private Article article;

	@ManyToOne
	@JoinColumn(name = "from_users_seq")
	private User fromUser;

	@ManyToOne
	@JoinColumn(name = "to_users_seq")
	private User toUser;

	@Column(name = "is_read")
	private Byte isRead;

	@Column(name = "type")
	private String type;

	@Builder
	public Notification(final Long seq, final LocalDateTime registeredTime, final Article article, final User fromUser,
		final User toUser, final Byte isRead, final String type) {
		this.seq = seq;
		this.registeredTime = registeredTime;
		this.article = article;
		this.fromUser = fromUser;
		this.toUser = toUser;
		this.isRead = isRead;
		this.type = type;
	}

	public Notification() {
	}

	public void updateIsRead(){
		this.isRead = 0;
	}
}