package com.a608.modac.service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.stereotype.Service;

import com.a608.modac.model.chat.ChatMessage;
import com.a608.modac.model.chat.ChatMessageRequest;
import com.a608.modac.model.chat.ChatMessageResponse;
import com.a608.modac.model.chat.ChatRoom;
import com.a608.modac.model.chat.ChatRoomDto;
import com.a608.modac.model.chat.ChatRoomType;
import com.a608.modac.model.chat.MessageType;
import com.a608.modac.model.follow.Follow;
import com.a608.modac.model.user.User;
import com.a608.modac.model.user.UserResponse;
import com.a608.modac.repository.ChatMessageRepository;
import com.a608.modac.repository.ChatRoomRepository;
import com.a608.modac.repository.FollowRepository;
import com.a608.modac.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@EnableRedisRepositories
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService {

	private final ChatMessageRepository chatMessageRepository;
	private final ChatRoomRepository chatRoomRepository;
	private final UserRepository userRepository;
	private final FollowRepository followRepository;

	@Override
	public ChatMessageResponse saveMessage(final ChatMessageRequest chatMessageRequest) {
		final ChatRoom chatRoom = chatRoomRepository.findById(chatMessageRequest.getChatRoomsSeq())
			.orElseThrow(NoSuchElementException::new);
		final User user = userRepository.findById(chatMessageRequest.getUsersSeq())
			.orElseThrow(NoSuchElementException::new);

		final ChatMessage chatMessage = isAddEnterMessage(chatMessageRequest, chatRoom, user); // 입장 메시지 추가.
		final ChatMessage saveChatMessage = isSaveChatMessage(chatMessageRequest, chatMessage);// 채팅 메시지 저장.

		return ChatMessageResponse.fromEntity(saveChatMessage, user);
	} // 채팅 메시지 저장.

	@Override
	public List<ChatMessageResponse> findMessagesByChatRoomsSeq(final Long roomsSeq, final Pageable pageable) {
		final List<ChatMessage> allByChatRoomSeq = chatMessageRepository.findChatMessagesByChatRoom_Seq(roomsSeq,
			pageable);

		return allByChatRoomSeq.stream()
			.map(chatMessage -> ChatMessageResponse.fromEntity(chatMessage, chatMessage.getUser()))
			.collect(Collectors.toList());
	} // 채팅 메시지 찾기.

	@Override
	public void updateLastMessage(final ChatMessageRequest chatMessageRequest) {
		chatRoomRepository.findById(chatMessageRequest.getChatRoomsSeq());

		chatRoomRepository.findById(chatMessageRequest.getChatRoomsSeq())
			.ifPresent(room -> room.updateChatRoom(room.getLastMessageSeq(), room.getLastMessageTime()));
	} // 채팅 메시지 업데이트.

	@Override
	public ChatRoomDto findChatRoomBySeq(final Long roomsSeq) {
		final ChatRoom chatRoom = chatRoomRepository.findById(roomsSeq).orElseThrow(NoSuchElementException::new);

		return ChatRoomDto.builder()
			.seq(roomsSeq)
			.lastMessageSeq(chatRoom.getLastMessageSeq())
			.lastMessageTime(chatRoom.getLastMessageTime())
			.build();
	} // 특정 채팅룸 찾기. -> 입장할 때 사용.

	public List<ChatRoomDto> findAllChatRoomsByFollowingsSeq(final Long userSeq) {
		final List<Follow> follows = followRepository.findAllByFromUser_SeqOrToUser_Seq(userSeq, userSeq);
		HashMap<Long, ChatRoom> chatRooms = new HashMap<>();
		final User myUser = userRepository.findById(userSeq).orElseThrow(() -> new NoSuchElementException("NoUser"));

		//  user가 속한 모든 팔로우 관계의 채팅방에서
		for (Follow follow : follows) {

			User talker; // 나랑 대화하는 상대방 유저

			// 1. 기본적으로 내가 FromUser라고 가정해볼께 (일단 특정 user를 가리켜야 오류가 안나서 그래!)
			//    그럼 상대방은 ToUser 유저일꺼야 맞지
			talker = follow.getToUser();

			// 2. 근데 내가 사실 ToUser였다면 상대방은 FromUser일꺼야
			//    그렇다면 맞게 정정해주자
			if (follow.getToUser().equals(myUser)) {
				talker = follow.getFromUser();
			}
			ChatRoom chatRoom = follow.getChatRoom();
			// 최근 메세지가 존재하는 채팅방에 상대방의 usersSeq를 key로, chatRoom을 value로 넣어주기
			if (chatRoom.getLastMessageSeq() != null) {
				chatRooms.put(talker.getSeq(), chatRoom);
			}
		}
		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++"+chatRooms);
		List<ChatRoomDto> chatRoomDtos = new ArrayList<>();
		for (Map.Entry<Long, ChatRoom> entry : chatRooms.entrySet()) {
			System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++"+entry);
			UserResponse talker = new UserResponse( // 상대방
				userRepository.findById(entry.getKey()).orElseThrow(() -> new NoSuchElementException("NoUser")));
			ChatRoom chatRoom = entry.getValue(); // 채팅방

			ChatMessage chatMessage = chatMessageRepository.findById(chatRoom.getLastMessageSeq())
				.orElseThrow(NoSuchElementException::new);
			ChatRoomDto chatRoomDto = ChatRoomDto.builder()
				.seq(chatRoom.getSeq())
				.lastMessageSeq(chatRoom.getLastMessageSeq())
				.lastMessage(chatMessage.getMessage())
				.lastMessageTime(chatRoom.getLastMessageTime())
				.talker(talker).build();

			chatRoomDtos.add(chatRoomDto);
		}

		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++"+chatRoomDtos);
		return chatRoomDtos;
	} // 특정 유저의 모든 채팅룸 찾기. -> 팔로잉 관계에서 1:1 채팅방 정보를 모두 가져와서 프론트로 전달.

	private static ChatMessage isAddEnterMessage(final ChatMessageRequest chatMessageRequest, final ChatRoom chatRoom,
		final User user) {

		ChatMessage chatMessage = null;

		if (chatMessageRequest.getMessageType().type().equals(MessageType.valueOf("TALK").type())) {
			System.out.println("++++++++++++++++++++++++++++++++++++++++++++TALK");
			chatMessage = saveTalkMessage(chatMessageRequest, chatRoom, user);
		}

		if (chatMessageRequest.getMessageType().type().equals(MessageType.valueOf("ENTER").type())) {
			chatMessage = saveEnterMessage(chatMessageRequest, chatRoom, user);
		}

		if (chatMessageRequest.getMessageType().type().equals(MessageType.valueOf("LEAVE").type())) {
			chatMessage = saveLeaveMessage(chatMessageRequest, chatRoom, user);
		}

		return chatMessage;
	}

	private static ChatMessage saveLeaveMessage(final ChatMessageRequest chatMessageRequest, final ChatRoom chatRoom,
		final User user) {

		return ChatMessage.builder()
			.chatRoom(chatRoom)
			.user(user)
			.message(user.getNickname() + "님이 방을 나갔습니다.")
			.sendTime(chatMessageRequest.getSendTime())
			.build();
	}

	private static ChatMessage saveEnterMessage(final ChatMessageRequest chatMessageRequest, final ChatRoom chatRoom,
		final User user) {

		return ChatMessage.builder()
			.chatRoom(chatRoom)
			.user(user)
			.message(user.getNickname() + "님이 대화를 시작했습니다.")
			.sendTime(chatMessageRequest.getSendTime())
			.build();
	}

	private static ChatMessage saveTalkMessage(final ChatMessageRequest chatMessageRequest, final ChatRoom chatRoom,
		final User user) {

		return ChatMessage.builder()
			.chatRoom(chatRoom)
			.user(user)
			.message(chatMessageRequest.getMessage())
			.sendTime(chatMessageRequest.getSendTime())
			.build();
	}

	private ChatMessage isSaveChatMessage(final ChatMessageRequest chatMessageRequest, final ChatMessage chatMessage) {
		ChatMessage saveChatMessage = null;

		if (chatMessageRequest.getChatRoomType().type().equals(ChatRoomType.DIRECT.type())) {
			// saveChatMessage = chatMessageRepository.save(Objects.requireNonNull(chatMessage));
			saveChatMessage = chatMessageRepository.save(chatMessage);
		}

		return saveChatMessage;
	}

}
