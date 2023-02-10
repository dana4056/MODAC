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

import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.stereotype.Service;

import com.a608.modac.model.chat.ChatMessage;
import com.a608.modac.model.chat.ChatRoom;
import com.a608.modac.model.chat.ChatRoomDto;
import com.a608.modac.model.chat.ChatRoomType;
import com.a608.modac.model.chat.DirectMessage;
import com.a608.modac.model.chat.DirectMessageDto;
import com.a608.modac.model.chat.MessageType;
import com.a608.modac.model.follow.Follow;
import com.a608.modac.model.user.User;
import com.a608.modac.repository.ChatDirectRepository;
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
	private final ChatDirectRepository chatDirectRepository;
	private final RedisTemplate<String, DirectMessage> redisTemplate;

	public List<DirectMessage> findAllMessagesByDirectChatRoomsSeq(final String chatRoomsSeq, final Pageable pageable) {
		final List<DirectMessage> chatCachesByChatRoomsSeq = chatDirectRepository.findDirectMessagesByChatRoomsSeq(
			chatRoomsSeq, pageable);

		System.out.println(chatCachesByChatRoomsSeq.size());

		return chatCachesByChatRoomsSeq;
	} // DM 특정 채팅방 메시지 목록 조회.

	@Override
	public DirectMessage enterDirectChatRoom(final DirectMessageDto directMessageDto) {
		final Optional<User> user = userRepository.findById(directMessageDto.getUsersSeq());

		String addMessage = "";

		if (user.isPresent()) {
			if (directMessageDto.getMessageType().type().equals(MessageType.valueOf("ENTER").type())) {
				addMessage = "님이 대화를 시작했습니다.";
			}
		}

		final DirectMessage directMessage = DirectMessage.builder()
			.chatRoomsSeq(String.valueOf(directMessageDto.getChatRoomsSeq()))
			.userNickName(user.get().getNickname())
			.sendTime(directMessageDto.getSendTime())
			.message(directMessageDto.getMessage() + addMessage)
			.build();

		final ListOperations<String, DirectMessage> stringDirectMessageListOperations = redisTemplate.opsForList();
		stringDirectMessageListOperations.rightPush(String.valueOf(directMessage.getChatRoomsSeq()), directMessage);

		return chatDirectRepository.save(directMessage);
	} // DM 채팅방 입장.

	@Override
	public DirectMessage saveDirectMessage(final DirectMessageDto directMessageDto) {
		final Optional<User> userOptional = userRepository.findById(directMessageDto.getUsersSeq());
		final ListOperations<String, DirectMessage> stringDirectMessageListOperations = redisTemplate.opsForList();

		DirectMessage directMessage = null;

		if (directMessageDto.getChatRoomType().type().equals(ChatRoomType.DIRECT.type())) {
			directMessage = DirectMessage.builder()
				.chatRoomsSeq(String.valueOf(directMessageDto.getChatRoomsSeq()))
				.userNickName(userOptional.get().getNickname())
				.sendTime(directMessageDto.getSendTime())
				.message(directMessageDto.getMessage())
				.build();

			stringDirectMessageListOperations.rightPush(String.valueOf(directMessageDto.getChatRoomsSeq()),
				directMessage);
		}

		return chatDirectRepository.save(Objects.requireNonNull(directMessage));
	} // DM 채팅 메시지 저장.

	@Override
	public void updateLastMessage(final DirectMessageDto directMessageDto) {
		final Optional<ChatRoom> chatRoomOptional = chatRoomRepository.findById(directMessageDto.getChatRoomsSeq());

		chatRoomOptional.ifPresent(chatRoom -> chatRoomRepository.findById(directMessageDto.getChatRoomsSeq())
			.ifPresent(room -> room.updateChatRoom(chatRoom.getLastMessageSeq(), chatRoom.getLastMessageTime())));

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

		return chatRooms;
	}
	// 특정 유저의 모든 채팅룸 찾기. -> 팔로잉 관계에서 1:1 채팅방 정보를 모두 가져와서 프론트로 전달.

}
