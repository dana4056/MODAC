package com.a608.modac.model.chat;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ChatRoomResponse {
	private Long seq;
	private Long lastMessageSeq;
	private String lastMessageTime;

	@Builder
	public ChatRoomResponse(final Long seq, final Long lastMessageSeq, final String lastMessageTime) {
		this.seq = seq;
		this.lastMessageSeq = lastMessageSeq;
		this.lastMessageTime = lastMessageTime;
	}

	public static ChatRoomResponse fromEntity(final ChatRoom chatRoom){
		return ChatRoomResponse.builder()
			.seq(chatRoom.getSeq())
			.lastMessageSeq(chatRoom.getLastMessageSeq())
			.lastMessageTime(chatRoom.getLastMessageTime())
			.build();
	}

}
