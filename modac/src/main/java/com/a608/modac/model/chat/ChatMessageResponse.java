package com.a608.modac.model.chat;

import com.a608.modac.model.user.User;
import com.a608.modac.model.user.UserResponse;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ChatMessageResponse {
	private final Long seq;
	private final UserResponse userResponse;
	private final Long chatRoomsSeq;
	private final String sendTime;
	private final String message;

	@Builder
	public ChatMessageResponse(final Long seq, final UserResponse userResponse, final Long chatRoomsSeq,
		final String sendTime,
		final String message) {
		this.seq = seq;
		this.userResponse = userResponse;
		this.chatRoomsSeq = chatRoomsSeq;
		this.sendTime = sendTime;
		this.message = message;
	}

	public static ChatMessageResponse fromEntity(final ChatMessage chatMessage, final User user) {
		return ChatMessageResponse.builder()
			.seq(chatMessage.getSeq())
			.chatRoomsSeq(chatMessage.getSeq())
			.userResponse(new UserResponse(user))
			.message(chatMessage.getMessage())
			.sendTime(chatMessage.getSendTime())
			.build();
	}

}
