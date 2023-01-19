package com.a608.modac.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

public class ArticleRequest {
	@Getter
	@AllArgsConstructor
	public class createArticleRequest {
		private final Long usersSeq;
		private final Long todosSeq;
		private final int publicType;
		private final String filepath;
	}
}
