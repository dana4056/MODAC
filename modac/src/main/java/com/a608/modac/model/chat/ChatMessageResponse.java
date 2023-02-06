package com.a608.modac.model.chat;

import com.a608.modac.model.user.User;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ChatMessageResponse {
	private Long seq;
	private User user;
	private ChatRoom chatRoom;
	private String sendTime;
	private String message;

	@Builder
	public ChatMessageResponse(final ChatMessage chatMessage) {
		this.seq = chatMessage.getSeq();
		this.user = chatMessage.getUser();
		this.chatRoom = chatMessage.getChatRoom();
		this.sendTime = chatMessage.getSendTime();
		this.message = chatMessage.getMessage();
	}

	public static ChatMessageResponse fromEntity(final ChatMessage chatMessage) {
		return ChatMessageResponse.builder()
			.chatMessage(chatMessage)
			.build();
	}

}
