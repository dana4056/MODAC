package com.a608.modac.model.comment;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.a608.modac.model.article.Article;
import com.a608.modac.model.user.User;

import lombok.Builder;
import lombok.Getter;

@Getter
@Entity
@Table(name = "comments")
public class Comment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long seq;

	@Column(name="content")
	private String content;

	@Column(name="registered_time")
	private String registeredTime;

	@ManyToOne
	@JoinColumn(name="articles_seq")
	private Article article;

	@ManyToOne
	@JoinColumn(name="users_seq")
	private User user;

	@Builder
	public Comment(final Long seq, final String content, final String registeredTime, final Article article, final User user) {
		this.seq = seq;
		this.content = content;
		this.registeredTime = registeredTime;
		this.article = article;
		this.user = user;
	}

	public Comment() {
	}
}
