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
import org.hibernate.annotations.DynamicInsert;

@Getter
@Entity
@ToString
@DynamicInsert
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
	private Byte publicType;

	@Column(name="view_count")
	private Integer viewCount;

	@Column(name="like_count")
	private Integer likeCount;

	@Column(name="comment_count")
	private Integer commentCount;

	@Column(name="total_second")
	private String totalSecond;

	@ManyToOne
	@JoinColumn(name="users_seq")
	private User user;

	@ManyToOne
	@JoinColumn(name="categories_name")
	private Category category;

	@Builder
	public Article(final Long seq, final String title, final String filepath, final String registeredTime, final Byte publicType, final Integer viewCount,
		final Integer likeCount, final Integer commentCount, final String totalSecond, final User user, final Category category) {
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

	// 좋아요수 업데이트 (+1 or -1)
	public void updateLikeCount(Integer cnt){
		this.likeCount += cnt;
	}

	public void updateCommentCount(Integer cnt){
		this.commentCount += cnt;
	}

	public void upViewCount(){
		this.viewCount++;
	}


}
