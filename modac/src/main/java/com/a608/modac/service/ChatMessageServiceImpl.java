package com.a608.modac.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.a608.modac.model.chat.ChatMessage;
import com.a608.modac.model.chat.ChatMessageRequest;
import com.a608.modac.model.chat.ChatMessageResponse;
import com.a608.modac.model.chat.ChatRoom;
import com.a608.modac.model.user.User;
import com.a608.modac.repository.ChatMessageRepository;
import com.a608.modac.repository.ChatRoomRepository;
import com.a608.modac.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChatMessageServiceImpl implements ChatMessageService {

	private final ChatMessageRepository chatMessageRepository;
	private final ChatRoomRepository chatRoomRepository;
	private final UserRepository userRepository;

	@Override
	public ChatMessageResponse saveMessage(final ChatMessageRequest chatMessageRequest) {
		final ChatRoom chatRoom = chatRoomRepository.findById(chatMessageRequest.getChatRoomsSeq())
			.orElseThrow(NoSuchElementException::new);
		final User user = userRepository.findById(chatMessageRequest.getUsersSeq())
			.orElseThrow(NoSuchElementException::new);

		final ChatMessage chatMessage = ChatMessage.builder()
			.seq(chatMessageRequest.getSeq())
			.chatRoom(chatRoom)
			.user(user)
			.message(chatMessageRequest.getMessage())
			.sendTime(chatMessageRequest.getSendTime())
			.build();

		chatMessageRepository.save(chatMessage);

		return ChatMessageResponse.fromEntity(chatMessage);
	} // 채팅 메시지 저장.

	@Override
	public List<ChatMessageResponse> findMessagesByChatRoomsSeq(final Long roomsSeq) {
		final List<ChatMessage> allByChatRoomSeq = chatMessageRepository.findChatMessagesByChatRoom_Seq(roomsSeq);

		return allByChatRoomSeq.stream()
			.map(ChatMessageResponse::fromEntity)
			.collect(Collectors.toList());
	}

}
