package com.a608.modac.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.a608.modac.model.chat.ChatMessage;
import com.a608.modac.model.chat.ChatMessageRequest;
import com.a608.modac.model.chat.ChatMessageResponse;
import com.a608.modac.model.chat.ChatRoom;
import com.a608.modac.model.chat.ChatRoomRequest;
import com.a608.modac.model.chat.ChatRoomResponse;
import com.a608.modac.model.user.User;
import com.a608.modac.model.user.UserResponse;
import com.a608.modac.service.ChatMessageService;
import com.a608.modac.service.ChatRoomService;
import com.a608.modac.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/chat")
public class ChatController {
	// ChatController 역할
	// 채팅방 생성, 삭제 -> StudyRoom 에서 해결.
	// 일대일, 일대다 공통 -> 채팅방 입장, 퇴장, 메시지 전송.
	// 일대일 -> 채팅 메시지 저장, 특정 채팅방 조회(입장 과정)
	// 일대다 -> 채팅 메시지는 프론트 뷰 스토어에 저장.
	// 일대일 -> 유저 정보 기반으로 전체 채팅방 조회.
	private final ChatRoomService chatRoomService;
	private final ChatMessageService chatMessageService;
	private final SimpMessageSendingOperations simpMessageSendingOperations;
	private final UserService userService;

	@PostMapping("/rooms")
	public ResponseEntity<ChatRoom> createChatRoom(@RequestBody final ChatRoomRequest chatRoomRequest) {
		final ChatRoom chatRoom = chatRoomService.createChatRoom(chatRoomRequest);

		return new ResponseEntity<>(chatRoom, HttpStatus.CREATED);
	} // 채팅방 생성. -> Study Room

	@GetMapping("/rooms/{roomsSeq}")
	public ResponseEntity<ChatRoomResponse> findChatRoom(@PathVariable final Long roomsSeq) {
		final ChatRoomResponse chatRoomBySeq = chatRoomService.findChatRoomBySeq(roomsSeq);

		return new ResponseEntity<>(chatRoomBySeq, HttpStatus.OK);
	} // 특정 채팅방 조회

	@GetMapping("/rooms")
	public ResponseEntity<List<ChatRoomResponse>> findAllChatRoom() {
		final List<ChatRoomResponse> allChatRooms = chatRoomService.findAllChatRooms();

		return new ResponseEntity<>(allChatRooms, HttpStatus.OK);
	} // 모든 채팅방 조회 -> 채팅방 리스트 반환.

	@GetMapping("/rooms/{roomsSeq}/messages")
	public ResponseEntity<List<ChatMessageResponse>> findMessagesByRoomsSeq(@PathVariable final Long roomsSeq) {
		final List<ChatMessageResponse> messagesByChatRoomsSeq = chatMessageService.findMessagesByChatRoomsSeq(
			roomsSeq);

		return new ResponseEntity<>(messagesByChatRoomsSeq, HttpStatus.OK);
	}

	@MessageMapping(value = "/rooms/enter")
	public void enterChatRoom(@Payload final ChatMessageRequest chatMessageRequest) {
		final UserResponse userBySeq = userService.findUserBySeq(chatMessageRequest.getSeq());
		final User user = User.builder().seq(chatMessageRequest.getSeq()).build();
		final ChatRoom chatRoom = ChatRoom.builder().seq(chatMessageRequest.getSeq()).build();

		final ChatMessage chatMessage = ChatMessage.builder()
			.seq(chatMessageRequest.getSeq())
			.user(user)
			.message(userBySeq.getNickname() + " 님이 입장하셨습니다.")
			.sendTime(chatMessageRequest.getSendTime())
			.chatRoom(chatRoom)
			.build();

		// 채팅방 유저 증가?
		// 유저 정보 세션에 저장?

		simpMessageSendingOperations.convertAndSend("/topic/chat/rooms" + chatMessage.getChatRoom().getSeq(),
			chatMessage.getMessage());
	} // 방 입장.

	@MessageMapping(value = "/message")
	public void sendMessage(@Payload final ChatMessageRequest chatMessageRequest) {
		final ChatMessageResponse chatMessageResponse = chatMessageService.saveMessage(chatMessageRequest);

		simpMessageSendingOperations.convertAndSend("/topic/chat/rooms" + chatMessageResponse.getChatRoom().getSeq(),
			chatMessageResponse);
	} // 단체방 구독자에게 메시지 보내기.

}
