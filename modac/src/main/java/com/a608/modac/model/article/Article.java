package com.a608.modac.model.article;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.a608.modac.model.category.Category;
import com.a608.modac.model.user.User;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Entity
@ToString
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
	private LocalDateTime registeredTime;

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

	@ManyToOne
	@JoinColumn(name="users_seq")
	private User user;

	@ManyToOne
	@JoinColumn(name="categories_seq")
	private Category category;

	@Builder
	public Article(final Long seq, final String title, final String filepath, final LocalDateTime registeredTime, final int publicType, final int viewCount,
		final int likeCount, final int commentCount, final String totalSecond, final User user, final Category category) {
		this.seq = seq;
		this.title = title;
		this.filepath = filepath;
		this.registeredTime = registeredTime;
		this.publicType = publicType;
		this.viewCount = viewCount;
		this.likeCount = likeCount;
		this.commentCount = commentCount;
		this.totalSecond = totalSecond;
		this.user = user;
		this.category = category;
	}

	public Article() {
	}
}
