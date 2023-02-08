package com.a608.modac.model.chat;

import com.a608.modac.model.user.User;
import com.a608.modac.model.user.UserResponse;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ChatMessageResponse {
	private final UserResponse user;
	private final Long chatRoomsSeq;
	private final String sendTime;
	private final String message;

	@Builder
	public ChatMessageResponse(final UserResponse user, final Long chatRoomsSeq,
		final String sendTime, final String message) {
		this.user = user;
		this.chatRoomsSeq = chatRoomsSeq;
		this.sendTime = sendTime;
		this.message = message;
	}

	public static ChatMessageResponse fromEntity(final ChatMessage chatMessage, final User user) {
		return ChatMessageResponse.builder()
			.chatRoomsSeq(chatMessage.getSeq())
			.user(new UserResponse(user))
			.message(chatMessage.getMessage())
			.sendTime(chatMessage.getSendTime())
			.build();
	}

}
