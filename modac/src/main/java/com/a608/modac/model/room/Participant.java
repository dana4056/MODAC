package com.a608.modac.model.room;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import com.a608.modac.model.membership.Membership;
import com.a608.modac.model.user.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Participant {
	@Column(name = "users_seq")
	private Long usersSeq;
	@Column(name = "nickname")
	private String nickname;
	@Column(name = "status")
	private Byte status;           		// 참가자 상태 (0:공부중, 1:휴식중, 2:자리비움)
	@Column(name = "cat_skin")
	private Byte catSkin;        		// UI로 표시될 고양이 스킨 번호
	@Column(name = "categories_name")
	private String categoriesName;    	// 현재공부중인 todo의 카테고리

	@Builder
	public Participant(User user) {
		this.usersSeq = user.getSeq();
		this.nickname = user.getNickname();
		this.status = 0;	// 맨처음 방에 들어갈 땐 공부중으로 상태 설정
		this.catSkin = user.getCatSkin();
	}
}
