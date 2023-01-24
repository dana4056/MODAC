package com.a608.modac.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.a608.modac.model.guest.GuestPK;
import com.a608.modac.model.guest.GuestRequest;
import com.a608.modac.model.guest.GuestResponse;
import com.a608.modac.service.GuestService;

@RestController
@RequestMapping("/guest")
public class GuestController {

	private final GuestService guestService;

	public GuestController(final GuestService guestService) {
		this.guestService = guestService;
	}

	@GetMapping("/list/{usersSeq}")
	public ResponseEntity<List<GuestResponse>> findAllMyRooms(@PathVariable("usersSeq") final Long usersSeq){
		final List<GuestResponse> allMyRooms = guestService.findAllMyRooms(usersSeq);

		return new ResponseEntity<>(allMyRooms, HttpStatus.OK);
	}

	@GetMapping("/{usersSeq}/{roomsSeq}")
	public ResponseEntity<GuestResponse> findMyRoom(@PathVariable("usersSeq") final Long usersSeq,
		@PathVariable("roomsSeq") final Long roomsSeq){
		final GuestPK guestPk = GuestPK.builder().usersSeq(usersSeq).roomsSeq(roomsSeq).build();
		final GuestResponse myRoom = guestService.findMyRoom(guestPk);

		return new ResponseEntity<>(myRoom, HttpStatus.OK);
	}

	@DeleteMapping("/{usersSeq}/{roomsSeq}")
	public ResponseEntity<Void> deleteTodo(@PathVariable("usersSeq") final Long usersSeq,
		@PathVariable("roomsSeq") final Long roomsSeq){
		final GuestPK guestPk = GuestPK.builder().usersSeq(usersSeq).roomsSeq(roomsSeq).build();
		guestService.deleteGuestInfo(guestPk);

		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Void> addGuestInfo(@RequestBody final GuestRequest guestRequest){
		guestService.addGuestInfo(guestRequest);

		return new ResponseEntity<>(HttpStatus.OK);
	}

}
