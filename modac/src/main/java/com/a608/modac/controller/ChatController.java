package com.a608.modac.controller;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.a608.modac.model.chat.ChatMessageRequest;
import com.a608.modac.model.chat.ChatMessageResponse;
import com.a608.modac.model.chat.ChatRoomDto;
import com.a608.modac.model.chat.DirectMessage;
import com.a608.modac.model.chat.DirectMessageDto;
import com.a608.modac.model.user.UserResponse;
import com.a608.modac.service.ChatService;
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

	// 테스트 목록: DM 채팅방 목록 조회, DM 채팅방 메시지 목록조회, DM 채팅방 입장, DM 메시지 보내기(메시지 수정), DM 채팅 마지막 메시지 변경
	// ChatRoomDto 유저 객체 추가.

	private final ChatService chatService;
	private final SimpMessageSendingOperations simpMessageSendingOperations;
	private final UserService userService;

	@GetMapping("/rooms")
	public ResponseEntity<List<ChatRoomDto>> findAllChatRoomsByUser(@RequestParam final Long fromUsersSeq,
		@RequestParam final Long toUsersSeq) {
		final List<ChatRoomDto> chatRoomDto = chatService.findAllChatRoomsByFollowingsSeq(fromUsersSeq,
			toUsersSeq);

		return new ResponseEntity<>(chatRoomDto, HttpStatus.OK);
	} // 유저의 1:1 모든 채팅방 조회 -> 모든 1:1 채팅방 리스트 반환. -> 다나가 바꿈.

	@GetMapping("/rooms/{chatRoomsSeq}/messages")
	public ResponseEntity<?> findAllMessageFromRedis(@PathVariable final Long chatRoomsSeq,
		@PageableDefault(size = 20, sort = "sendTime", direction = Sort.Direction.DESC)
		Pageable pageable) {
		final List<DirectMessage> allMessagesFromRedis = chatService.findAllMessagesByDirectChatRoomsSeq(
			String.valueOf(chatRoomsSeq), pageable);

		return new ResponseEntity<>(allMessagesFromRedis, HttpStatus.OK);
	} // redis DM 특정 채팅방 메시지 전체 조회.

//	@MessageMapping(value = "/rooms/enter/direct")
	@PostMapping("/rooms/enter/direct")
	public ResponseEntity<Void> enterDirectChatRoom(final DirectMessageDto directMessageDto) {
		final DirectMessage directMessage = chatService.saveDirectMessage(directMessageDto);

		simpMessageSendingOperations.convertAndSend(
			"/queue/chat/rooms/enter/direct" + directMessage.getChatRoomsSeq(),
			directMessage.getMessage());

		return new ResponseEntity<>(HttpStatus.OK);
	} // 채팅방 입장(구독) -> 1:1 대화.

//	@MessageMapping(value = "/messages/direct")
	@PostMapping(value = "/messages/direct")
	public ResponseEntity<Void> sendDirectMessage(@RequestBody final DirectMessageDto directMessageDto) {
		// MessageMapping으로 바꾸면서 @RequestBody 지워야함.
		final DirectMessage directMessage = chatService.saveDirectMessage(directMessageDto);
		chatService.updateLastMessage(directMessageDto);

		simpMessageSendingOperations.convertAndSend(
			"/queue/chat/rooms/enter/direct" + directMessageDto.getChatRoomsSeq(),
			directMessageDto);

		return new ResponseEntity<>(HttpStatus.OK);
	} // (채팅방 구독자에게) 메시지 보내기. -> // redis DM 채팅 메시지 저장.

	@MessageMapping(value = "/rooms/enter/group")
	public void enterGroupChatRoom(ChatMessageRequest chatMessageRequest) {
		final UserResponse user = userService.findUserBySeq(chatMessageRequest.getUsersSeq());
		final ChatRoomDto chatRoom = chatService.findChatRoomBySeq(chatMessageRequest.getChatRoomsSeq());

		ChatMessageResponse.builder()
			.message(chatMessageRequest.getMessage())
			.chatRoomsSeq(chatRoom.getSeq())
			.sendTime(chatMessageRequest.getSendTime())
			.user(user)
			.build();

		simpMessageSendingOperations.convertAndSend(
			"/topic/chat/rooms/enter/group/" + chatMessageRequest.getChatRoomsSeq(),
			chatMessageRequest);
	} // 채팅방 입장(구독) -> 그룹 대화.

	@MessageMapping("/messages/group")
	public void sendGroupMessage(final ChatMessageRequest chatMessageRequest) {
		final UserResponse userResponse = userService.findUserBySeq(chatMessageRequest.getUsersSeq());

		final ChatMessageResponse chatMessageResponse = ChatMessageResponse.builder()
			.user(userResponse)
			.chatRoomsSeq(chatMessageRequest.getChatRoomsSeq())
			.message(chatMessageRequest.getMessage())
			.sendTime(chatMessageRequest.getSendTime())
			.build();

		simpMessageSendingOperations.convertAndSend(
			"/topic/chat/rooms/enter/group/" + chatMessageResponse.getChatRoomsSeq(),
			chatMessageResponse);
	} // (채팅방 구독자에게) 메시지 보내기. -> 그룹 대화.



}
