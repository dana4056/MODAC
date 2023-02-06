package com.a608.modac.model.chat;

import com.a608.modac.model.user.User;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ChatMessageRequest {
	private Long seq;
	private Long usersSeq;
	private Long chatRoomsSeq;
	private String sendTime;
	private String message;
	private MessageType messageType;

	@Builder
	public ChatMessageRequest(final Long seq, final Long usersSeq, final Long chatRoomsSeq, final String sendTime,
		final String message, final MessageType messageType) {
		this.seq = seq;
		this.usersSeq = usersSeq;
		this.chatRoomsSeq = chatRoomsSeq;
		this.sendTime = sendTime;
		this.message = message;
		this.messageType = messageType;
	}

	public ChatMessage toEntity(final ChatMessageRequest chatMessageRequest, final ChatRoom chatRoom, final User user) {
		return ChatMessage.builder()
			.seq(seq)
			.user(user)
			.chatRoom(chatRoom)
			.message(chatMessageRequest.getMessage())
			.sendTime(chatMessageRequest.getSendTime())
			.build();
	}

}
