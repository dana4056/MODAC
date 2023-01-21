package com.a608.modac.model.user;

import lombok.Getter;

@Getter
public class UserRequest {
	private Long seq;
	private String id;
	private String nickname;
	private String email;
	private String password;

	public User toEntity(){
		return User.builder()
			.seq(seq)
			.id(id)
			.nickname(nickname)
			.email(email)
			.password(password)
			.build();
	}
}
