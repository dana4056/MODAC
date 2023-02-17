package com.a608.modac.model.participant;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ParticipantRequest {
	private Long usersSeq;
	private boolean attend;

	// 0214추가
	private Long roomsSeq;

	public ParticipantRequest(Long usersSeq, boolean attend, Long roomsSeq) {
		this.usersSeq = usersSeq;
		this.attend = attend;
		this.roomsSeq = roomsSeq;
	}
}
