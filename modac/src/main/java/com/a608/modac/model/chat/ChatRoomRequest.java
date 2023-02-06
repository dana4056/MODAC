package com.a608.modac.model.chat;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ChatRoomRequest {
	private Long seq;
	private Long lastMessageSeq;
	private String lastMessageTime;

	@Builder
	public ChatRoomRequest(final Long seq, final Long lastMessageSeq, final String lastMessageTime) {
		this.seq = seq;
		this.lastMessageSeq = lastMessageSeq;
		this.lastMessageTime = lastMessageTime;
	}

}
