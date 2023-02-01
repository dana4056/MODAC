package com.a608.modac.model.room;

import java.util.List;

import com.a608.modac.model.chatting.ChatRoom;
import com.a608.modac.model.participant.ParticipantResponse;
import com.a608.modac.model.user.User;

import lombok.Getter;

@Getter
public class RoomResponse {
	private final Long seq;
	private final String title;
	private final String description;
	private final Integer maxSize;
	private final Integer currentSize;
	private final String multiTheme;
	private final Integer publicType;
	private final String invitationCode;
	private final List<ParticipantResponse> participants;
	// private final Set<String> participants;
	private final User host;
	private final ChatRoom chatRoom;

	public RoomResponse(final Room room){
		this.seq = room.getSeq();
		this.title = room.getTitle();
		this.description = room.getDescription();
		this.maxSize = room.getMaxSize();
		this.currentSize = room.getCurrentSize();
		this.multiTheme = room.getMultiTheme();
		this.publicType = room.getPublicType();
		this.invitationCode = room.getInvitationCode();
		this.participants = room.getParticipant();
		this.host = room.getHost();
		this.chatRoom = room.getChatRoom();
	}

}
