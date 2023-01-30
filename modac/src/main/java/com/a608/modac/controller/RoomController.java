package com.a608.modac.controller;

import static com.a608.modac.model.room.RoomRequest.*;

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

	@PostMapping				// 멀티룸 생성
	public ResponseEntity<?> createRoom(@RequestBody final RoomRequest roomRequest){
		RoomResponse room = roomService.createRoom(roomRequest);
		return new ResponseEntity<>(room, HttpStatus.OK);
	}

	@GetMapping				// 멀티룸 목록 조회
	public ResponseEntity<?> findAllRooms(){
		final List<RoomResponse> allRooms = roomService.findAllRooms();
		return new ResponseEntity<>(allRooms, HttpStatus.OK);
	}

	@GetMapping("/{seq}")	// 멀티룸 조회
	public ResponseEntity<?> findRoomById(@PathVariable("seq") final Long seq){
		final RoomResponse roomById = roomService.findRoomById(seq);
		return new ResponseEntity<>(roomById, HttpStatus.OK);
	}

	@PutMapping("/{seq}")
	public ResponseEntity<?> updateTodo(@PathVariable("seq") final Long seq, @RequestBody RoomRequest roomRequest){
		roomService.updateRoom(seq, roomRequest);
		final List<RoomResponse> allRooms = roomService.findAllRooms();

		return new ResponseEntity<>(allRooms, HttpStatus.OK);
	}



	@DeleteMapping("/{seq}")
	public ResponseEntity<?> deleteTodo(@PathVariable("seq") final Long seq){
		roomService.deleteRoom(seq);

		return new ResponseEntity<>(HttpStatus.OK);
	}


}
