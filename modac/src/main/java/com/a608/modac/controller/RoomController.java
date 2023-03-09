package com.a608.modac.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.a608.modac.model.participant.ParticipantRequest;
import com.a608.modac.model.room.RoomRequest;
import com.a608.modac.model.room.RoomResponse;
import com.a608.modac.service.RoomService;

import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = { "*" })
@RequiredArgsConstructor
@RestController
@RequestMapping("/rooms")
public class RoomController {

	private final RoomService roomService;
	private final SimpMessageSendingOperations simpMessageSendingOperations;

	// public RoomController(final RoomService roomService) {
	// 	this.roomService = roomService;
	// }

	@PostMapping								// 멀티룸 생성
	public ResponseEntity<?> createRoom(@RequestBody final RoomRequest roomRequest){
		RoomResponse room = roomService.createRoom(roomRequest);
		return new ResponseEntity<>(room, HttpStatus.CREATED);
	}

	@GetMapping								// 멀티룸 목록 조회
	public ResponseEntity<?> findAllRooms(@RequestParam(value = "user") final Long usersSeq, @RequestParam(value = "keyword", required = false) final String keyword){
		// 모든 멀티룸 목록 조회
		if(keyword == null) {
			final List<RoomResponse> allRooms = roomService.findAllRooms(usersSeq);
			return new ResponseEntity<>(allRooms, HttpStatus.OK);
		}
		// 검색어가 포함된 멀티룸 목록 조회
		return new ResponseEntity<List<RoomResponse>>(roomService.searchRooms(usersSeq, keyword), HttpStatus.OK);
	}

	@GetMapping("/{seq}")					// 멀티룸 조회
	public ResponseEntity<?> findRoomById(@PathVariable("seq") final Long seq){
		final RoomResponse roomById = roomService.findRoomById(seq);
		return new ResponseEntity<RoomResponse>(roomById, HttpStatus.OK);
	}

	@PutMapping("/{seq}")					// 멀티룸 수정
	public ResponseEntity<?> updateTodo(@PathVariable("seq") final Long seq, @RequestBody RoomRequest roomRequest){
		RoomResponse roomResponse = roomService.updateRoom(seq, roomRequest);
		return new ResponseEntity<RoomResponse>(roomResponse, HttpStatus.CREATED);
	}

	@DeleteMapping("/{seq}")					// 멀티룸 삭제
	public ResponseEntity<?> deleteTodo(@PathVariable("seq") final Long seq){
		roomService.deleteRoom(seq);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PutMapping("/{seq}/join")				// 멀티룸 참여상태 변경
	public ResponseEntity<?> updateUserAttend(@PathVariable("seq") final Long seq, @RequestBody final ParticipantRequest participantRequest){
		roomService.updateUserAttend(seq, participantRequest);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping("/favorite")		// 내가 참여중인 비공개 멀티룸 목록 조회
	public ResponseEntity<?> findMyRooms(@RequestParam("user") Long usersSeq){
		final List<RoomResponse> myRooms = roomService.findMyRooms(usersSeq);
		return new ResponseEntity<>(myRooms, HttpStatus.OK);
	}

	@GetMapping("/{seq}/authorization")		// 참가자 인가 (비공개 멀티룸의 멤버인지 확인)
	public ResponseEntity<?> userAuthorization(@PathVariable("seq") final Long seq, @RequestParam("user") final Long usersSeq){
		boolean isMember = roomService.isMember(seq, usersSeq);
		return new ResponseEntity<>(isMember, HttpStatus.OK);
	}

	@GetMapping("/{seq}/authentication")		// 참가자 인증 (입력한 초대코드가 방의 코드와 일치하는지 확인)
	public ResponseEntity<?> userAuthentication(@PathVariable("seq") final Long seq, @RequestParam("code") final String code){
		boolean isSameCode = roomService.isSameCode(seq, code);
		return new ResponseEntity<>(isSameCode, HttpStatus.OK);
	}



	// @PostMapping("/{seq}/join")				// 멀티룸에 참여
	// public ResponseEntity<?> joinRoom(@PathVariable("seq") final Long seq, @RequestBody String usersSeq){
	// 	RoomResponse roomResponse = roomService.joinRoom(seq, Long.parseLong(usersSeq));
	// 	return new ResponseEntity<RoomResponse>(roomResponse, HttpStatus.OK);
	// }
	//
	// @DeleteMapping("/{seq}/join")			// 멀티룸에서 나가기
	// public ResponseEntity<?> exitRoom(@PathVariable("seq") final Long seq, @RequestParam("user") Long usersSeq){
	// 	roomService.exitRoom(seq, usersSeq);
	// 	return new ResponseEntity<>(HttpStatus.OK);
	// }



	@CrossOrigin(origins = { "*" })
	@MessageMapping(value = "/message/enter")		// @PostMapping("/{seq}/join") '멀티룸에 참여'였던것
	public ResponseEntity<Void> sendDirectMessage(@RequestBody final ParticipantRequest participantRequest) {
		//참가자 번호, 입퇴장문자열("ENTER", "EXIT"), 방번호가 담긴 DTO로 받아서


		// 멀티룸 퇴장 또는 입장 비즈니스 로직 호출하고 -> (true, false)에 따라 분기문
		if(participantRequest.isAttend()){
			System.out.println("참가요청");
			RoomResponse roomResponse = roomService.joinRoom(participantRequest.getRoomsSeq(), participantRequest.getUsersSeq());

			simpMessageSendingOperations.convertAndSend("/enter/rooms/" + participantRequest.getRoomsSeq(), "ENTER");
			System.out.println("/enter/rooms/" + participantRequest.getRoomsSeq()+"로 ENTER 보냄");
		}else{
			System.out.println("퇴장요청");
			roomService.exitRoom(participantRequest.getRoomsSeq(), participantRequest.getUsersSeq());
			simpMessageSendingOperations.convertAndSend("/enter/rooms/" + participantRequest.getRoomsSeq(), "EXIT");
		}

		// 입퇴장 문자열 종류 전송(구독자들에게) -> ("ENTER", "EXIT") 이거 그대로 -> 프론트에서 분기해서 처리

		return new ResponseEntity<>(HttpStatus.OK);
	} // 참가자 입퇴장 (구독자들에게 알리기)
}
