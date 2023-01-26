package com.a608.modac.model.notice;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;

import com.a608.modac.model.article.Like;
import com.a608.modac.model.comment.Comment;
import com.a608.modac.model.follow.Follow;
import com.a608.modac.model.user.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@ToString
@NoArgsConstructor
@DynamicInsert
@AllArgsConstructor
@Table(name = "notices")
public class Notice {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long seq;
	@Column(name = "message")
	private String message;

	@Column(name = "time")
	private String time;

	@Column(name = "status")
	private Byte status;

	@ManyToOne
	@JoinColumn(name = "users_seq")
	private User user;

	@OneToOne
	@JoinColumn(name = "followings_seq")
	private Follow follow;

	@OneToOne
	@JoinColumn(name = "likes_seq")
	private Like like;

	@OneToOne
	@JoinColumn(name = "comments_seq")
	private Comment comment;
	
}
