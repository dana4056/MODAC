package com.a608.modac.service;

import static com.a608.modac.model.room.RoomRequest.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.a608.modac.model.room.Room;
import com.a608.modac.model.user.User;
import com.a608.modac.repository.RoomRepository;
import com.a608.modac.model.room.RoomResponse;
import com.a608.modac.repository.UserRepository;

@Service
public class RoomServiceImpl implements RoomService{

	private final RoomRepository roomRepository;
	private final UserRepository userRepository;

	public RoomServiceImpl(RoomRepository roomRepository, UserRepository userRepository) {
		this.roomRepository = roomRepository;
		this.userRepository = userRepository;
	}

	@Override
	public RoomResponse findRoomById(final Long seq) {
		final Optional<Room> findRoomById = roomRepository.findById(seq);
		RoomResponse roomResponse = null;

		if(findRoomById.isPresent()){
			roomResponse = new RoomResponse(findRoomById.get());
		}

		return roomResponse;
	}

	@Override
	public List<RoomResponse> findAllRooms() {
		final List<Room> findAllRooms = roomRepository.findAll();

		return findAllRooms.stream().map(RoomResponse::new).collect(Collectors.toList());
	}

	@Override
	public void createRoom(final CreateRoomRequest createRoomRequest) {
		User user = userRepository.findById(createRoomRequest.getUserSeq()).orElseThrow(NoSuchElementException::new);
		roomRepository.save(createRoomRequest.toEntity(user));
	}

	@Override
	public void updateRoom(final UpdateRoomRequest updateRoomRequest) {
		final Optional<Room> findRoomById = roomRepository.findById(updateRoomRequest.getSeq());
		findRoomById.ifPresent(room -> room.updateRoom(updateRoomRequest.getTitle(),
			updateRoomRequest.getDescription(),
			updateRoomRequest.getMultiTheme())
		);

		roomRepository.save(findRoomById.get());
	}

	@Override
	public void deleteRoom(final Long seq) {
		roomRepository.deleteById(seq);
	}

}
