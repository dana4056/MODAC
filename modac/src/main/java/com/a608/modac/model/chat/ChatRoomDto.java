package com.a608.modac.model.chat;

import com.a608.modac.model.user.UserResponse;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ChatRoomDto {
	private final Long seq;
	private final String lastMessageSeq;
	private final String lastMessage;
	private final String lastMessageTime;
	private final UserResponse talker;

	@Builder
	public ChatRoomDto(final Long seq, final String lastMessageSeq, final String lastMessage, final String lastMessageTime,
		final UserResponse talker) {
		this.seq = seq;
		this.lastMessageSeq = lastMessageSeq;
		this.lastMessage = lastMessage;
		this.lastMessageTime = lastMessageTime;
		this.talker = talker;
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
