package com.a608.modac.model.chat;

import com.a608.modac.model.user.User;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ChatMessageRequest {
	private Long usersSeq;
	private Long chatRoomsSeq;
	private String sendTime;
	private String message;
	private MessageType messageType;
	private ChatRoomType chatRoomType;

	@Builder
	public ChatMessageRequest(Long usersSeq, Long chatRoomsSeq, String sendTime, String message,
		MessageType messageType,
		ChatRoomType chatRoomType) {
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
