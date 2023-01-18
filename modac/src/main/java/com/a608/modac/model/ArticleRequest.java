package com.a608.modac.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

public class ArticleRequest {
	@Getter
	@AllArgsConstructor
	public class Regist {
		private final long usersSeq;
		private final long categoriesSeq;
		private final int publicType;
		private final String filepath;
	}
}
