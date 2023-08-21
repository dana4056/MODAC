package com.a608.modac.controller;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.CrossOrigin;
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

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;

@Api(tags = "Chat Controller", description = "채팅 관련 API")
@RestController
@RequiredArgsConstructor
@CrossOrigin
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

	// 유저의 1:1 모든 채팅방 조회 -> 모든 1:1 채팅방 리스트 반환. -> 다나가 바꿈.
	@Operation(summary = "일대일 채팅방 목록 조회", description = "특정 사용자(user)가 참가 중인 일대일 채팅방 전부 조회")
	@GetMapping("/rooms")
	public ResponseEntity<List<ChatRoomDto>> findAllChatRoom(@RequestParam("user") final Long userSeq) {
		final List<ChatRoomDto> chatRoomDto = chatService.findAllChatRoomsByFollowingsSeq(userSeq);
		System.out.println(chatRoomDto);
		return new ResponseEntity<>(chatRoomDto, HttpStatus.OK);
	}

	// redis DM 특정 채팅방 메시지 전체 조회.
	@Operation(summary = "특정 일대일 채팅방 메시지 목로 조회", description = "특정 일대일 채팅방(chatRoomsSeq)에 기록된 메시지 전체 조회")
	@GetMapping("/rooms/{chatRoomsSeq}/messages")
	public ResponseEntity<?> findAllMessageFromRedis(@PathVariable final Long chatRoomsSeq,
		@PageableDefault(size = 20, sort = "sendTime", direction = Sort.Direction.ASC)
		Pageable pageable) {
		final List<DirectMessage> allMessagesFromRedis = chatService.findAllMessagesByDirectChatRoomsSeq(
			String.valueOf(chatRoomsSeq), pageable);

		return new ResponseEntity<>(allMessagesFromRedis, HttpStatus.OK);
	}

	// =================================== DM ==============================================================

	// (채팅방 구독자에게) 메시지 보내기. -> // redis DM 채팅 메시지 저장.
	@MessageMapping(value = "/messages/direct")
	public ResponseEntity<Void> sendDirectMessage(final DirectMessageDto directMessageDto) {
		final DirectMessage directMessage = chatService.saveDirectMessage(directMessageDto);
		chatService.updateLastMessage(directMessageDto, directMessage.getSeq());

		System.out.println("★★★★★★★★★★★★★★★★★★★★"+directMessageDto);
		simpMessageSendingOperations.convertAndSend(
			"/queue/chat/rooms/enter/direct/" +directMessageDto.getChatRoomsSeq(),
			directMessageDto);

		return new ResponseEntity<>(HttpStatus.OK);
	}

	// ====================================================================================================

	// 채팅방 입장(구독) -> 그룹 대화.
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
	}


	// (채팅방 구독자에게) 메시지 보내기. -> 그룹 대화.
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
	}



}
