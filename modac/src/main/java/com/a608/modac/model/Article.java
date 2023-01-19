package com.a608.modac.model;

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
@Table(name = "articles")
public class Article {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long seq;

	@Column(name="title")
	private String title;

	@Column(name="filepath")
	private String filepath;

	@Column(name="registered_time")
	private String registeredTime;

	@Column(name="public_type")
	private int publicType;

	@Column(name="view_count")
	private int viewCount;

	@Column(name="like_count")
	private int likeCount;

	@Column(name="comment_count")
	private int commentCount;

	@Column(name="total_second")
	private String totalSecond;

	@Column(name="users_seq")
	private Long usersSeq;

	@Column(name="categories_seq")
	private Long categoriesSeq;

	@Builder
	public Article(Long seq, String title, String filepath, String registeredTime, int publicType, int viewCount,
		int likeCount, int commentCount, String totalSecond, Long usersSeq, Long categoriesSeq) {
		this.seq = seq;
		this.title = title;
		this.filepath = filepath;
		this.registeredTime = registeredTime;
		this.publicType = publicType;
		this.viewCount = viewCount;
		this.likeCount = likeCount;
		this.commentCount = commentCount;
		this.totalSecond = totalSecond;
		this.usersSeq = usersSeq;
		this.categoriesSeq = categoriesSeq;
	}

	public Article() {
	}
}
