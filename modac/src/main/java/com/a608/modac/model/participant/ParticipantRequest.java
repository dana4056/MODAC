package com.a608.modac.model.participant;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ParticipantRequest {
	private Long usersSeq;
	private boolean attend;

	public ParticipantRequest(Long usersSeq, boolean attend) {
		this.usersSeq = usersSeq;
		this.attend = attend;
	}
}
