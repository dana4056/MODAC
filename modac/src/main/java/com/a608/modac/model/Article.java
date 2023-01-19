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
@Table(name = "Articles")
public class Article {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long seq;

	@Column(name="title")
	private String title;

	@Column(name="filepath")
	private String filepath;

	@Column(name="registed_time")
	private String registedTime;

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
	private long usersSeq;

	@Column(name="categories_seq")
	private long categoriesSeq;

	@Builder
	public Article(long seq, String title, String filepath, String registedTime, int publicType, int viewCount,
		int likeCount, int commentCount, String totalSecond, long usersSeq, long categoriesSeq) {
		this.seq = seq;
		this.title = title;
		this.filepath = filepath;
		this.registedTime = registedTime;
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
