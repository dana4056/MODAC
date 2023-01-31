package com.a608.modac.model.user;

import com.a608.modac.model.membership.Membership;

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

	public User toEntity(Membership membership, Byte skin){
		return User.builder()
			.seq(seq)
			.id(id)
			.nickname(nickname)
			.email(email)
			.password(password)
			.membership(membership)
			.catSkin(skin)
			.build();
	}
}
