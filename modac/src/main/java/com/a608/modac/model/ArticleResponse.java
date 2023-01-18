package com.a608.modac.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

public class ArticleResponse {
	@Getter
	@Setter
	@RequiredArgsConstructor(staticName = "info")
	public class ArticleResponseInfo {
		private final long seq;
		private final String title;
		private final String filepath;
		private final String registedTime;
		private final int publicType;
		private final int viewCount;
		private final int commentCount;
		private final String totalSecond;
		private final long usersSeq;
		private final long categoriesSeq;
	}
}
