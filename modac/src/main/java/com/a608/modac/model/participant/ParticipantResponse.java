package com.a608.modac.model.participant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ParticipantResponse {
	private Long usersSeq;
	private String nickname;
	private Byte status;           		// 참가자 상태 (0:공부중, 1:휴식중, 2:자리비움)
	private Byte attend;				// 현재 해당 멀티룸에 참여중인지(화면이 해당룸인지)
	private Byte catSkin;        		// UI로 표시될 고양이 스킨 번호
	private String categoriesName;    	// 현재공부중인 todo의 카테고리


}
