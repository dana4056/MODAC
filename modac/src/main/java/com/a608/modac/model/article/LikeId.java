package com.a608.modac.model.article;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Data;

@Data
@Embeddable
public class LikeId implements Serializable {

	@Column(name = "articles_seq")
	private Long articlesSeq;

	@Column(name = "users_seq")
	private Long usersSeq;

	public LikeId(Long articlesSeq, Long usersSeq) {
		this.articlesSeq = articlesSeq;
		this.usersSeq = usersSeq;
	}

	public LikeId() {
	}
}
