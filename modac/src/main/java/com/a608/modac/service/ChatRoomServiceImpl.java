package com.a608.modac.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.a608.modac.model.chat.ChatRoom;
import com.a608.modac.model.chat.ChatRoomRequest;
import com.a608.modac.model.chat.ChatRoomResponse;
import com.a608.modac.repository.ChatRoomRepository;
import com.a608.modac.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ChatRoomServiceImpl implements ChatRoomService {
	private final ChatRoomRepository chatRoomRepository;
	private final UserRepository userRepository;

	@Override
	public ChatRoom createChatRoom(final ChatRoomRequest chatRoomRequest) {
		final ChatRoom chatRoom = new ChatRoom(
			chatRoomRequest.getSeq(),
			chatRoomRequest.getLastMessageSeq(),
			chatRoomRequest.getLastMessageTime()
		);

		return chatRoomRepository.save(chatRoom);
	} // 채팅룸 생성.

	@Override
	public ChatRoomResponse findChatRoomBySeq(final Long roomsSeq) {
		final ChatRoom chatRoom = chatRoomRepository.findById(roomsSeq).orElseThrow(NoSuchElementException::new);

		return ChatRoomResponse.builder()
			.seq(roomsSeq)
			.lastMessageSeq(chatRoom.getLastMessageSeq())
			.lastMessageTime(chatRoom.getLastMessageTime())
			.build();
	} // 특정 채팅룸 찾기.

	@Override
	public List<ChatRoomResponse> findAllChatRooms() {
		return chatRoomRepository.findAll().stream()
			.map(ChatRoomResponse::fromEntity)
			.collect(Collectors.toList());
	} // 모든 채팅룸 찾기.


}
