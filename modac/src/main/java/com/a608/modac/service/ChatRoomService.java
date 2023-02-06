package com.a608.modac.service;

import java.util.List;

import com.a608.modac.model.chat.ChatRoom;
import com.a608.modac.model.chat.ChatRoomRequest;
import com.a608.modac.model.chat.ChatRoomResponse;

public interface ChatRoomService {
	ChatRoom createChatRoom(final ChatRoomRequest chatRoomRequest);
	ChatRoomResponse findChatRoomBySeq(final Long roomsSeq);
	List<ChatRoomResponse> findAllChatRooms();
}
