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
import com.a608.modac.model.user.UserResponse;
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

		ChatRoom chatRoom = chatRoomRepository.findById(directMessageDto.getChatRoomsSeq())
			.orElseThrow(() -> new NoSuchElementException("NoChatRoom"));

		System.out.println("chatRoom:"+chatRoom);

		chatRoom.updateChatRoom(directMessageDto.getMessage(), directMessageDto.getSendTime());
		chatRoomRepository.save(chatRoom);

	} // 채팅 메시지 업데이트.

	@Override
	public ChatRoomDto findChatRoomBySeq(final Long roomsSeq) {
		final ChatRoom chatRoom = chatRoomRepository.findById(roomsSeq).orElseThrow(NoSuchElementException::new);

		return ChatRoomDto.builder()
			.seq(roomsSeq)
			.lastMessage(chatRoom.getLastMessageSeq())
			.lastMessageTime(chatRoom.getLastMessageTime())
			.build();
	} // 특정 채팅룸 찾기. -> 입장할 때 사용.

	public List<ChatRoomDto> findAllChatRoomsByFollowingsSeq(final Long userSeq) {
		final List<Follow> follows = followRepository.findAllByFromUser_SeqOrToUser_Seq(userSeq, userSeq);
		System.out.println("=============================================follws"+follows);

		HashMap<Long, ChatRoom> chatRooms = new HashMap<>();
		final User myUser = userRepository.findById(userSeq).orElseThrow(() -> new NoSuchElementException("NoUser"));
		System.out.println("=============================================myUser"+myUser);

		//  user가 속한 모든 팔로우 관계의 채팅방에서
		for (Follow follow : follows) {

			User talker; // 나랑 대화하는 상대방 유저

			talker = follow.getToUser();

			if (follow.getToUser().equals(myUser)) {
				talker = follow.getFromUser();
			}
			ChatRoom chatRoom = follow.getChatRoom();
			if (chatRoom.getLastMessageSeq() != null) {
				chatRooms.put(talker.getSeq(), chatRoom);
			}
		}
		List<ChatRoomDto> chatRoomDtos = new ArrayList<>();
		for (Map.Entry<Long, ChatRoom> entry : chatRooms.entrySet()) {
			UserResponse talker = new UserResponse( // 상대방
				userRepository.findById(entry.getKey()).orElseThrow(() -> new NoSuchElementException("NoUser")));
			ChatRoom chatRoom = entry.getValue(); // 채팅방

			// ChatMessage chatMessage = chatMessageRepository.findById()
			// 	.orElseThrow(NoSuchElementException::new);
			ChatRoomDto chatRoomDto = ChatRoomDto.builder()
				.seq(chatRoom.getSeq())
				.lastMessageSeq(chatRoom.getLastMessageSeq())
				.lastMessageTime(chatRoom.getLastMessageTime())
				.talker(talker).build();

			chatRoomDtos.add(chatRoomDto);
		}

		return chatRoomDtos;
	} // 특정 유저의 모든 채팅룸 찾기. -> 팔로잉 관계에서 1:1 채팅방 정보를 모두 가져와서 프론트로 전달.

}
