package com.a608.modac.service;

import java.util.ArrayList;
import java.util.List;
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
	} // 특정 유저의 모든 채팅룸 찾기. -> 팔로잉 관계에서 1:1 채팅방 정보를 모두 가져와서 프론트로 전달.

	private static ChatMessage isAddEnterMessage(final ChatMessageRequest chatMessageRequest, final ChatRoom chatRoom,
		final User user) {

		ChatMessage chatMessage = null;

		if (chatMessageRequest.getMessageType().type().equals(MessageType.valueOf("TALK").type())) {
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
			saveChatMessage = chatMessageRepository.save(Objects.requireNonNull(chatMessage));
		}

		return saveChatMessage;
	}

}
