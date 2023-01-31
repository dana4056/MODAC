package com.a608.modac.model.user;

import lombok.Getter;

@Getter
public class UserResponse {

	private Long seq;
	private String id;
	private String nickname;
	private String email;
	private Byte catSkin;
	private String singleTheme;
	private Long totalSecond;
	private String membershipGrade;

	public UserResponse(User user){
		this.seq = user.getSeq();
		this.id = user.getId();
		this.nickname = user.getNickname();
		this.email = user.getEmail();
		this.catSkin = user.getCatSkin();
		this.singleTheme = user.getSingleTheme();
		this.totalSecond = user.getTotalSecond();
		this.membershipGrade = user.getMembership().getGrade();
	}
}
