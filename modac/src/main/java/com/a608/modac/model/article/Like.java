package com.a608.modac.model.article;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.a608.modac.model.user.User;

import lombok.Builder;
import lombok.Getter;

@Getter
@Entity
@Table(name = "likes")
public class Like {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long seq;

	@ManyToOne
	@JoinColumn(name = "articles_seq")
	private Article article;

	@ManyToOne
	@JoinColumn(name = "users_seq")
	private User user;


	@Builder
	public Like(Long seq, Article article, User user) {
		this.seq = seq;
		this.article = article;
		this.user = user;
	}

	public Like() {
	}

}
