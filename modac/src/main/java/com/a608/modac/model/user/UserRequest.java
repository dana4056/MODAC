package com.a608.modac.model.user;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class UserRequest {
	private Long seq;
	private String id;
	private String nickname;
	private String email;
	private String password;

	public User toEntity(Byte skin) {
		return User.builder()
			.seq(seq)
			.id(id)
			.nickname(nickname)
			.email(email)
			.password(password)
			.catSkin(skin)
			.build();
	}
}
