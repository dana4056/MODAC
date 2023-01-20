package com.a608.modac.model.comment;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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
	private LocalDateTime registeredTime;

	@Column(name="article_seq")
	private Long articlesSeq;

	@Column(name="users_seq")
	private Long usersSeq;

	@Builder
	public Comment(Long seq, String content, LocalDateTime registeredTime, Long articlesSeq, Long usersSeq) {
		this.seq = seq;
		this.content = content;
		this.registeredTime = registeredTime;
		this.articlesSeq = articlesSeq;
		this.usersSeq = usersSeq;
	}

	public Comment() {
	}
}
