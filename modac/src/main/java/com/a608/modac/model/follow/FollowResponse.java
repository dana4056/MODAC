package com.a608.modac.model.follow;

import com.a608.modac.model.chat.ChatRoom;
import com.a608.modac.model.user.User;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class FollowResponse {
	private final Long seq;
	private final User fromUser;
	private final User toUser;
	private final ChatRoom chatRoom;

	@Builder
	public FollowResponse(final Long seq, final User fromUser, final User toUser, final ChatRoom chatRoom) {
		this.seq = seq;
		this.fromUser = fromUser;
		this.toUser = toUser;
		this.chatRoom = chatRoom;
	}

	public static FollowResponse fromEntity(final Follow follow) {
		return FollowResponse.builder()
			.seq(follow.getSeq())
			.chatRoom(follow.getChatRoom())
			.fromUser(follow.getFromUser())
			.toUser(follow.getToUser())
			.build();
	}

}
