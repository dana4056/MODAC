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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.a608.modac.model.chat.ChatMessageRequest;
import com.a608.modac.model.chat.ChatMessageResponse;
import com.a608.modac.model.chat.ChatRoomDto;
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

	//	@GetMapping("/rooms/{roomsSeq}")
	//	public ResponseEntity<ChatRoomDto> findChatRoom(@PathVariable final Long roomsSeq) {
	//		final ChatRoomDto chatRoomBySeq = chatService.findChatRoomBySeq(roomsSeq);
	//
	//		return new ResponseEntity<>(chatRoomBySeq, HttpStatus.OK);
	//	} // 특정 채팅방 조회. -> 1:1 채팅방 들어갈때

	@GetMapping("/rooms")
	public ResponseEntity<List<ChatRoomDto>> findAllChatRoom(@RequestParam("user") final Long userSeq) {
		final List<ChatRoomDto> chatRoomDto = chatService.findAllChatRoomsByFollowingsSeq(userSeq);

		return new ResponseEntity<>(chatRoomDto, HttpStatus.OK);
	} // 유저의 1:1 모든 채팅방 조회 -> 모든 1:1 채팅방 리스트 반환. -> 바꿔야함. api 명세서 참고.

	@GetMapping("/rooms/{roomsSeq}/messages")
	public ResponseEntity<List<ChatMessageResponse>> findMessagesByRoomsSeq(@PathVariable final Long roomsSeq,
		@PageableDefault(size = 20, sort = "sendTime", direction = Sort.Direction.DESC)
		Pageable pageable) {
		final List<ChatMessageResponse> messagesByChatRoomsSeq = chatService.findMessagesByChatRoomsSeq(roomsSeq,
			pageable);

		return new ResponseEntity<>(messagesByChatRoomsSeq, HttpStatus.OK);
	} // 특정 채팅방 메시지 불러오기. -> 1:1 채팅방 메시지 불러오기.(가능)

	@MessageMapping(value = "/rooms/enter/direct")
	public ResponseEntity<Void> enterDirectChatRoom(@Payload final ChatMessageRequest chatMessageRequest) {
		final ChatMessageResponse chatMessageResponse = chatService.saveMessage(chatMessageRequest);

		simpMessageSendingOperations.convertAndSend(
			"/queue/chat/rooms/enter/direct" + chatMessageResponse.getChatRoomsSeq(),
			chatMessageResponse.getMessage());

		return new ResponseEntity<>(HttpStatus.OK);
	} // 채팅방 입장(구독) -> 1:1 대화.

	// 1:1 대화는 방 채팅이 존재하지 않는다.

	@MessageMapping(value = "/messages/direct")
	public ResponseEntity<Void> sendDirectMessage(final ChatMessageRequest chatMessageRequest) {
		final ChatMessageResponse chatMessageResponse = chatService.saveMessage(chatMessageRequest);
		chatService.updateLastMessage(chatMessageRequest);

		simpMessageSendingOperations.convertAndSend(
			"/queue/chat/rooms/enter/direct" + chatMessageResponse.getChatRoomsSeq(),
			chatMessageResponse);

		return new ResponseEntity<>(HttpStatus.OK);
	} // (채팅방 구독자에게) 메시지 보내기. -> 1:1대화

	@MessageMapping(value = "/rooms/enter/group")
	public void enterGroupChatRoom(ChatMessageRequest chatMessageRequest) {
		chatService.updateLastMessage(chatMessageRequest);

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
		chatService.updateLastMessage(chatMessageRequest);

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
