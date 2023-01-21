package com.a608.modac.service;

import static com.a608.modac.model.room.RoomRequest.*;

import java.util.List;

import com.a608.modac.model.room.RoomResponse;

public interface RoomService {
	RoomResponse findRoomById(final Long seq);
	List<RoomResponse> findAllRooms();
	void createRoom(final CreateRoomRequest createRoomRequest);
	void updateRoom(final UpdateRoomRequest updateRoomRequest);
	void deleteRoom(final Long seq);
}
