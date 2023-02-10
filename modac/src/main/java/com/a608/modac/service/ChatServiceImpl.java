package com.a608.modac.service;

import java.util.ArrayList;
import java.util.List;
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

	public List<ChatRoomDto> findAllChatRoomsByFollowingsSeq(final Long fromUsersSeq, final Long toUsersSeq) {
		final List<Follow> follows = followRepository.findFollowsByFromUser_SeqAndToUser_Seq(
			fromUsersSeq, toUsersSeq);

		final List<ChatRoomDto> chatRooms = new ArrayList<>();

		for (final Follow follow : follows) {
			final Optional<ChatMessage> chatMessageById = chatMessageRepository.findById(
				follow.getChatRoom().getLastMessageSeq());
			String chatMessage = null;

			if (chatMessageById.isPresent()) {
				chatMessage = chatMessageById.get().getMessage();
			}

			if (Optional.ofNullable(chatMessage).isPresent()) {
				final ChatRoomDto chatRoom = ChatRoomDto.builder()
					.seq(follow.getChatRoom().getSeq())
					.message(chatMessage)
					.lastMessageTime(follow.getChatRoom().getLastMessageTime())
					.build();

				chatRooms.add(chatRoom);
			}
		}

		return chatRooms;
	}
	// 특정 유저의 모든 채팅룸 찾기. -> 팔로잉 관계에서 1:1 채팅방 정보를 모두 가져와서 프론트로 전달.

}
