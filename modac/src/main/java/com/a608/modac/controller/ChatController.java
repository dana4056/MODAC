package com.a608.modac.controller;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.a608.modac.model.chat.ChatMessageRequest;
import com.a608.modac.model.chat.ChatMessageResponse;
import com.a608.modac.model.chat.ChatRoomDto;
import com.a608.modac.model.follow.FollowResponse;
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
	private UserService userService;

	@GetMapping("/rooms/{roomsSeq}")
	public ResponseEntity<ChatRoomDto> findChatRoom(@PathVariable final Long roomsSeq) {
		final ChatRoomDto chatRoomBySeq = chatRoomService.findChatRoomBySeq(roomsSeq);

		return new ResponseEntity<>(chatRoomBySeq, HttpStatus.OK);
	} // 특정 채팅방 조회. -> 1:1 채팅방 들어갈때

	@GetMapping("/rooms/{followingsSeq}")
	public ResponseEntity<List<FollowResponse>> findAllChatRoom(@PathVariable final Long followingsSeq) {
		final List<FollowResponse> allChatRoomsByFollowingsSeq = chatRoomService.findAllChatRoomsByFollowingsSeq(
			followingsSeq);

		return new ResponseEntity<>(allChatRoomsByFollowingsSeq, HttpStatus.OK);
	} // 유저의 1:1 모든 채팅방 조회 -> 모든 1:1 채팅방 리스트 반환.

	@GetMapping("/rooms/{roomsSeq}/messages")
	public ResponseEntity<List<ChatMessageResponse>> findMessagesByRoomsSeq(@PathVariable final Long roomsSeq,
		@PageableDefault(size = 20, sort = "sendTime", direction = Sort.Direction.DESC)
		Pageable pageable) {
		final List<ChatMessageResponse> messagesByChatRoomsSeq = chatMessageService.findMessagesByChatRoomsSeq(roomsSeq,
			pageable);

		return new ResponseEntity<>(messagesByChatRoomsSeq, HttpStatus.OK);
	} // 특정 채팅방 메시지 불러오기.

	@MessageMapping(value = "/rooms/enter/direct")
	public ResponseEntity<Void> enterDirectChatRoom(@Payload final ChatMessageRequest chatMessageRequest) {
		final ChatMessageResponse chatMessageResponse = chatMessageService.saveMessage(chatMessageRequest);

		simpMessageSendingOperations.convertAndSend("/queue/chat/rooms" + chatMessageResponse.getChatRoomsSeq(),
			chatMessageResponse.getMessage());

		return new ResponseEntity<>(HttpStatus.OK);
	} // 채팅방 입장(구독) -> 1:1 대화.

	// 1:1 대화는 방 채팅이 존재하지 않는다.

	@MessageMapping(value = "/chat/messages/direct")
	public ResponseEntity<Void> sendDirectMessage(@Payload final ChatMessageRequest chatMessageRequest) {
		final ChatMessageResponse chatMessageResponse = chatMessageService.saveMessage(chatMessageRequest);
		chatMessageService.updateLastMessage(chatMessageRequest);

		simpMessageSendingOperations.convertAndSend("/queue/chat/rooms" + chatMessageResponse.getChatRoomsSeq(),
			chatMessageResponse);

		return new ResponseEntity<>(HttpStatus.OK);
	} // (채팅방 구독자에게) 메시지 보내기. -> 1:1대화

	@MessageMapping(value = "/rooms/enter/group")
	public void enterGroupChatRoom(ChatMessageRequest chatMessageRequest) {
		chatMessageService.updateLastMessage(chatMessageRequest);

		final UserResponse user = userService.findUserBySeq(chatMessageRequest.getSeq());
		final ChatRoomDto chatRoom = chatRoomService.findChatRoomBySeq(chatMessageRequest.getChatRoomsSeq());

		ChatMessageResponse.builder()
			.seq(chatRoom.getSeq())
			.message(chatMessageRequest.getMessage())
			.chatRoomsSeq(chatRoom.getSeq())
			.sendTime(chatMessageRequest.getSendTime())
			.userResponse(user)
			.build();

		simpMessageSendingOperations.convertAndSend("/topic/chat/rooms/enter/group/" + chatMessageRequest.getSeq(),
			chatMessageRequest);
	} // 채팅방 입장(구독) -> 그룹 대화.

	@MessageMapping(value = "/rooms/leave/group")
	public ResponseEntity<Void> leaveGroupChatRoom(@Payload final ChatMessageRequest chatMessageRequest) {
		simpMessageSendingOperations.convertAndSend("/topic/chat/rooms" + chatMessageRequest.getSeq(),
			chatMessageRequest.getMessage());

		return new ResponseEntity<>(HttpStatus.OK);
	} // 채팅방 퇴장(구독) -> 그룹 대화. -> 필요없나? 프론트에서 바로 쏴주니까?

	@MessageMapping("/messages/group")
	public void sendGroupMessage(final ChatMessageRequest chatMessageRequest) {
		chatMessageService.updateLastMessage(chatMessageRequest);
		System.out.println(chatMessageRequest.getChatRoomsSeq());
		System.out.println(chatMessageRequest.getMessage());

		final ChatMessageResponse chatMessageResponse = ChatMessageResponse.builder()
			.seq(chatMessageRequest.getSeq())
			.userResponse(userService.findUserBySeq(chatMessageRequest.getUsersSeq()))
			.chatRoomsSeq(chatMessageRequest.getChatRoomsSeq())
			.message(chatMessageRequest.getMessage())
			.sendTime(chatMessageRequest.getSendTime())
			.build();

		simpMessageSendingOperations.convertAndSend(
			"/topic/chat/rooms/enter/group/" + chatMessageResponse.getChatRoomsSeq(),
			chatMessageResponse);
	} // (채팅방 구독자에게) 메시지 보내기. -> 그룹 대화.

}
