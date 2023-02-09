package com.a608.modac.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.a608.modac.model.chat.ChatMessageRequest;
import com.a608.modac.model.chat.ChatMessageResponse;
import com.a608.modac.model.chat.ChatRoomDto;

public interface ChatService {
	ChatMessageResponse saveMessage(final ChatMessageRequest chatMessageRequest);
	List<ChatMessageResponse> findMessagesByChatRoomsSeq(final Long roomsSeq, final Pageable pageable);
	void updateLastMessage(final ChatMessageRequest chatMessageRequest);
	ChatRoomDto findChatRoomBySeq(final Long roomsSeq);
	List<ChatRoomDto> findAllChatRoomsByFollowingsSeq(final Long userSeq);

}

