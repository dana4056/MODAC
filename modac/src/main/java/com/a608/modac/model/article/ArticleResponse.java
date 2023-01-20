package com.a608.modac.model.article;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

public class ArticleResponse {
	@Getter
	@AllArgsConstructor
	public class readArticleResponse {
		private final Long seq;
		private final String title;
		private final String filepath;
		private final String registeredTime;
		private final int publicType;
		private final int viewCount;
		private final int commentCount;
		private final String totalSecond;
		private final Long usersSeq;
		private final Long categoriesSeq;
	}
}
