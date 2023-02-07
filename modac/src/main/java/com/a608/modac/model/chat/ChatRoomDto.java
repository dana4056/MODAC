package com.a608.modac.model.chat;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ChatRoomDto {
	private final Long seq;
	private final Long lastMessageSeq;
	private final String lastMessageTime;

	@Builder
	public ChatRoomDto(final Long seq, final Long lastMessageSeq, final String lastMessageTime) {
		this.seq = seq;
		this.lastMessageSeq = lastMessageSeq;
		this.lastMessageTime = lastMessageTime;
	}

	public static ChatRoomDto fromEntity(final ChatRoom chatRoom) {
		return ChatRoomDto.builder()
			.seq(chatRoom.getSeq())
			.lastMessageSeq(chatRoom.getLastMessageSeq())
			.lastMessageTime(chatRoom.getLastMessageTime())
			.build();
	}

	public static ChatRoom toEntity(final ChatRoom chatRoom) {
		return ChatRoom.builder()
			.seq(chatRoom.getSeq())
			.lastMessageSeq(chatRoom.getLastMessageSeq())
			.lastMessageTime(chatRoom.getLastMessageTime())
			.build();
	}

}
