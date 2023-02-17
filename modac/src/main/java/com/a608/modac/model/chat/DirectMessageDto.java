package com.a608.modac.model.chat;

import com.a608.modac.model.user.User;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public class DirectMessageDto {
	private Long usersSeq;
	private Long chatRoomsSeq;
	private String sendTime;
	private String message;
	private MessageType messageType;
	private ChatRoomType chatRoomType;

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
