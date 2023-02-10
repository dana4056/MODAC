package com.a608.modac.model.chat;

import com.a608.modac.model.user.User;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class DirectMessageDto {
	private final Long usersSeq;
	private final Long chatRoomsSeq;
	private final String sendTime;
	private final String message;
	private final MessageType messageType;
	private final ChatRoomType chatRoomType;

	@Builder
	public DirectMessageDto(final Long usersSeq, final Long chatRoomsSeq, final String sendTime, final String message,
		final MessageType messageType,
		final ChatRoomType chatRoomType) {
		this.usersSeq = usersSeq;
		this.chatRoomsSeq = chatRoomsSeq;
		this.sendTime = sendTime;
		this.message = message;
		this.messageType = messageType;
		this.chatRoomType = chatRoomType;
	}

	public DirectMessage toEntity(final DirectMessageDto directMessageDto, final User user) {
		return DirectMessage.builder()
			.userNickName(user.getNickname())
			.message(directMessageDto.getMessage())
			.sendTime(directMessageDto.getSendTime())
			.chatRoomsSeq(String.valueOf(directMessageDto.getChatRoomsSeq()))
			.build();
	}

}
