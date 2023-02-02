package com.a608.modac.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

import com.a608.modac.model.room.RoomRequest;
import com.a608.modac.model.room.RoomResponse;
import com.a608.modac.service.RoomService;

@CrossOrigin(origins = { "*" })
@RestController
@RequestMapping("/rooms")
public class RoomController {

	private final RoomService roomService;

	public RoomController(final RoomService roomService) {
		this.roomService = roomService;
	}

	@PostMapping								// 멀티룸 생성
	public ResponseEntity<?> createRoom(@RequestBody final RoomRequest roomRequest){
		RoomResponse room = roomService.createRoom(roomRequest);
		return new ResponseEntity<>(room, HttpStatus.OK);
	}

	@GetMapping								// 멀티룸 목록 조회
	public ResponseEntity<?> findAllRooms(@RequestParam(value = "user", required = false) Long userSeq){
		if(userSeq == null){
			// 모든 멀티룸 목록 조회
			final List<RoomResponse> allRooms = roomService.findAllRooms();
			return new ResponseEntity<>(allRooms, HttpStatus.OK);
		}else{
			// 내가 참여중인 비공개 멀티룸 목록 조회
			final List<RoomResponse> myRooms = roomService.findMyRooms(userSeq);
			return new ResponseEntity<>(myRooms, HttpStatus.OK);
		}
	}

	@GetMapping("/{seq}")					// 멀티룸 조회
	public ResponseEntity<?> findRoomById(@PathVariable("seq") final Long seq){
		final RoomResponse roomById = roomService.findRoomById(seq);
		return new ResponseEntity<RoomResponse>(roomById, HttpStatus.OK);
	}

	@PutMapping("/{seq}")					// 멀티룸 수정
	public ResponseEntity<?> updateTodo(@PathVariable("seq") final Long seq, @RequestBody RoomRequest roomRequest){
		RoomResponse roomResponse = roomService.updateRoom(seq, roomRequest);
		return new ResponseEntity<RoomResponse>(roomResponse, HttpStatus.OK);
	}

	@DeleteMapping("/{seq}")					// 멀티룸 삭제
	public ResponseEntity<?> deleteTodo(@PathVariable("seq") final Long seq){
		roomService.deleteRoom(seq);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PostMapping("/{seq}/join")				// 멀티룸에 참여
	public ResponseEntity<?> joinRoom(@PathVariable("seq") final Long seq, @RequestBody String userSeq){
		RoomResponse roomResponse = roomService.joinRoom(seq, Long.parseLong(userSeq));
		return new ResponseEntity<RoomResponse>(roomResponse, HttpStatus.OK);
	}

	@DeleteMapping("/{seq}/join")			// 멀티룸에서 나가기
	public ResponseEntity<?> exitRoom(@PathVariable("seq") final Long seq, @RequestBody String userSeq){
		roomService.exitRoom(seq, Long.parseLong(userSeq));
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PutMapping("/{seq}/join")				// 멀티룸 참여상태 변경
	public ResponseEntity<?> updateUserAttend(@PathVariable("seq") final Long seq, @RequestParam("user") final Long userSeq, @RequestParam("attend") final boolean isAttended){
		roomService.updateUserAttend(seq, userSeq, isAttended);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping("/{seq}/authorization")		// 참가자 인가 (비공개 멀티룸의 멤버인지 확인)
	public ResponseEntity<?> userAuthorization(@PathVariable("seq") final Long seq, @RequestParam("user") final Long userSeq){
		boolean isMember = roomService.isMember(seq, userSeq);
		return new ResponseEntity<>(isMember, HttpStatus.OK);
	}

	@GetMapping("/{seq}/authentication")		// 참가자 인증 (입력한 초대코드가 방의 코드와 일치하는지 확인)
	public ResponseEntity<?> userAuthentication(@PathVariable("seq") final Long seq, @RequestParam("code") final String code){
		boolean isSameCode = roomService.isSameCode(seq, code);
		return new ResponseEntity<>(isSameCode, HttpStatus.OK);
	}

}
