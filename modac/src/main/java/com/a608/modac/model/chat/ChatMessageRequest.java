package com.a608.modac.model.chat;

import com.a608.modac.model.user.User;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ChatMessageRequest {
	private final Long usersSeq;
	private final Long chatRoomsSeq;
	private final String sendTime;
	private final String message;
	private final MessageType messageType;
	private final ChatRoomType chatRoomType;

	@Builder
	public ChatMessageRequest( final Long usersSeq, final Long chatRoomsSeq, final String sendTime,
		final String message, final MessageType messageType, final ChatRoomType chatRoomType) {
		this.usersSeq = usersSeq;
		this.chatRoomsSeq = chatRoomsSeq;
		this.sendTime = sendTime;
		this.message = message;
		this.messageType = messageType;
		this.chatRoomType = chatRoomType;
	}

	public ChatMessage toEntity(final ChatMessageRequest chatMessageRequest, final ChatRoom chatRoom, final User user) {
		return ChatMessage.builder()
			.user(user)
			.chatRoom(chatRoom)
			.message(chatMessageRequest.getMessage())
			.sendTime(chatMessageRequest.getSendTime())
			.build();
	}

}
