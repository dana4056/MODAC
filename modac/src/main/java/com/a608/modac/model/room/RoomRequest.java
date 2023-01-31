package com.a608.modac.model.room;

import lombok.Getter;

import com.a608.modac.model.chatting.ChatRoom;
import com.a608.modac.model.participant.Participant;
import com.a608.modac.model.user.User;

@Getter
public class RoomRequest {
	private String title;
	private String description;
	private Integer maxSize;
	private String multiTheme;
	private Integer publicType;

	private Long usersSeq;

	public Room toEntity(User host, ChatRoom chatRoom, String invitationCode){
		return Room.builder()
			.title(title)
			.description(description)
			.maxSize(maxSize)
			.multiTheme(multiTheme)
			.publicType(publicType)
			.invitationCode(invitationCode)
			.host(host)
			.chatRoom(chatRoom)
			.build();
	}
}
