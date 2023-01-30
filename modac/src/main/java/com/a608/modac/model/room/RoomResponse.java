package com.a608.modac.model.room;

import com.a608.modac.model.chatting.ChatRoom;
import com.a608.modac.model.user.User;

import lombok.Getter;

@Getter
public class RoomResponse {
	private final Long seq;
	private final String title;
	private final String description;
	private final Integer maxSize;
	private final String multiTheme;
	private final Integer publicType;
	private final String invitationCode;
	private final User user;
	private final ChatRoom chatRoom;

	public RoomResponse(final Room room){
		this.seq = room.getSeq();
		this.title = room.getTitle();
		this.description = room.getDescription();
		this.maxSize = room.getMaxSize();
		this.multiTheme = room.getMultiTheme();
		this.publicType = room.getPublicType();
		this.invitationCode = room.getInvitationCode();
		this.user = room.getUser();
		this.chatRoom = room.getChatRoom();
	}

}
