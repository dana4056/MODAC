package com.a608.modac.model.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class UserRequest {
	private Long seq;
	private String id;
	private String nickname;
	private String email;
	private String password;

	public void setPassword(String password){
		this.password = password;
	}

	public User toEntity(Byte skin, String salt) {
		return User.builder()
			.seq(seq)
			.id(id)
			.nickname(nickname)
			.email(email)
			.password(password)
			.salt(salt)
			.catSkin(skin)
			.build();
	}
}
