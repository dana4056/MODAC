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

import com.a608.modac.model.room.RoomResponse;
import com.a608.modac.service.RoomService;

@CrossOrigin(origins = { "*" })
@RestController
@RequestMapping("/room")
public class RoomController {

	private final RoomService roomService;

	public RoomController(final RoomService roomService) {
		this.roomService = roomService;
	}

	@GetMapping("/{seq}")
	public ResponseEntity<RoomResponse> findRoomById(@PathVariable("seq") final Long seq){
		final RoomResponse roomById = roomService.findRoomById(seq);

		return new ResponseEntity<>(roomById, HttpStatus.OK);
	}

	@GetMapping("/list")
	public ResponseEntity<List<RoomResponse>> findAllRooms(){
		final List<RoomResponse> allRooms = roomService.findAllRooms();

		return new ResponseEntity<>(allRooms, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Void> createRoom(@RequestBody final CreateRoomRequest createRoomRequest){
		roomService.createRoom(createRoomRequest);

		return new ResponseEntity<>(HttpStatus.OK);
	}

	@DeleteMapping("/{seq}")
	public ResponseEntity<Void> deleteTodo(@PathVariable("seq") final Long seq){
		roomService.deleteRoom(seq);

		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PutMapping
	public ResponseEntity<List<RoomResponse>> updateTodo(@RequestBody UpdateRoomRequest updateRoomRequest){
		roomService.updateRoom(updateRoomRequest);
		final List<RoomResponse> allRooms = roomService.findAllRooms();

		return new ResponseEntity<>(allRooms, HttpStatus.OK);
	}

}
