package com.a608.modac.service;

import java.util.List;

import com.a608.modac.model.chat.ChatMessageRequest;
import com.a608.modac.model.chat.ChatMessageResponse;

public interface ChatMessageService {
	ChatMessageResponse saveMessage(final ChatMessageRequest chatMessageRequest);
	List<ChatMessageResponse> findMessagesByChatRoomsSeq(final Long roomsSeq);

}

