package com.a608.modac.model.article;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Getter;

@Getter
@Entity
@Table(name = "likes")
public class Like {
	@EmbeddedId
	private LikeId likeId;

	@Builder
	public Like(LikeId likeId) {
		this.likeId = likeId;
	}

	public Like() {
	}

}
