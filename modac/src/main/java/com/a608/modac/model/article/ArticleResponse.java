package com.a608.modac.model.article;

import java.time.LocalDateTime;
import java.util.List;

import com.a608.modac.model.user.User;

import lombok.Getter;

@Getter
public class ArticleResponse {
	private List<ArticleInfoResponse> articleInfoList;
	private Integer totalArticleCnt;
	private Integer totalPageCnt;

	public ArticleResponse(List<ArticleInfoResponse> articleInfoList, Integer totalArticleCnt, Integer totalPageCnt) {
		this.articleInfoList = articleInfoList;
		this.totalArticleCnt = totalArticleCnt;
		this.totalPageCnt = totalPageCnt;
	}

	@Getter
	public static class ArticleInfoResponse {
		private Long seq;
		private String title;
		private String filepath;
		private LocalDateTime registeredTime;
		private Byte publicType;
		private Integer viewCount;
		private Integer likeCount;
		private Integer commentCount;
		private String totalSecond;
		private User user;
		private String categoryName;

		public ArticleInfoResponse(Article article) {
			this.seq = article.getSeq();
			this.title = article.getTitle();
			this.filepath = article.getFilepath();
			this.registeredTime = article.getRegisteredTime();
			this.publicType = article.getPublicType();
			this.viewCount = article.getViewCount();
			this.likeCount = article.getLikeCount();
			this.commentCount = article.getCommentCount();
			this.totalSecond = article.getTotalSecond();
			this.user = article.getUser();
			this.categoryName = article.getCategory().getName();
		}

		public void readContentByFilepath(String content){
			this.filepath = content;
		}
	}
}
