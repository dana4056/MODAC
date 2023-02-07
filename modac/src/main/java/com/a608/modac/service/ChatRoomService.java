package com.a608.modac.service;

import java.util.List;

import com.a608.modac.model.chat.ChatRoomDto;
import com.a608.modac.model.follow.FollowResponse;

public interface ChatRoomService {
	ChatRoomDto findChatRoomBySeq(final Long roomsSeq);

	List<FollowResponse> findAllChatRoomsByFollowingsSeq(final Long followingsSeq);
}
