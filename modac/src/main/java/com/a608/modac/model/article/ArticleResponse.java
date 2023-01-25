package com.a608.modac.model.article;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
public class ArticleResponse {
	private Long seq;
	private String title;
	private String filepath;
	private LocalDateTime registeredTime;
	private int publicType;
	private int viewCount;
	private int commentCount;
	private String totalSecond;
	private Long usersSeq;
	private Long categoriesSeq;

	public ArticleResponse(Article article) {
		this.seq = article.getSeq();
		this.title = article.getTitle();
		this.filepath = article.getFilepath();
		this.registeredTime = article.getRegisteredTime();
		this.publicType = article.getPublicType();
		this.viewCount = article.getViewCount();
		this.commentCount = article.getCommentCount();
		this.totalSecond = article.getTotalSecond();
		this.usersSeq = article.getUsersSeq();
		this.categoriesSeq = article.getCategoriesSeq();
	}

}
