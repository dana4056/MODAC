package com.a608.modac.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.a608.modac.model.chatting.ChatRoom;
import com.a608.modac.model.room.Participant;
import com.a608.modac.model.room.Room;
import com.a608.modac.model.room.RoomRequest;
import com.a608.modac.model.user.User;
import com.a608.modac.model.user.UserRequest;
import com.a608.modac.repository.ChatRoomRepository;
import com.a608.modac.repository.RoomRepository;
import com.a608.modac.model.room.RoomResponse;
import com.a608.modac.repository.UserRepository;

@Service
public class RoomServiceImpl implements RoomService{

	private final RoomRepository roomRepository;
	private final UserRepository userRepository;
	private final ChatRoomRepository chatRoomRepository;

	public RoomServiceImpl(RoomRepository roomRepository, UserRepository userRepository,
		ChatRoomRepository chatRoomRepository) {
		this.roomRepository = roomRepository;
		this.userRepository = userRepository;
		this.chatRoomRepository = chatRoomRepository;
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
	public RoomResponse createRoom(final RoomRequest roomRequest) {
		User host = userRepository.findById(roomRequest.getUsersSeq()).orElseThrow(NoSuchElementException::new);
		String code = null;
		if(roomRequest.getPublicType() == 0){ // 비공개방일때
			// 초대코드 생성 (대충 6자리코드)
			double min = 100000;
			double max = 999999;
			int random = (int) ((Math.random() * (max - min)) + min);
			code = Integer.toString(random);
		}
		// 채팅방 생성
		Participant participant = new Participant(host);
		ChatRoom chatRoom = chatRoomRepository.save(new ChatRoom());
		Room save = roomRepository.save(roomRequest.toEntity(host, participant, chatRoom, code));
		return new RoomResponse(save);
	}

	@Override
	public RoomResponse updateRoom(final Long seq, final RoomRequest roomRequest) {
		Room room = roomRepository.findById(seq).orElseThrow(NoSuchElementException::new);
		room.updateRoom(roomRequest.getTitle(),
			roomRequest.getDescription(),
			roomRequest.getMultiTheme());
		return new RoomResponse(roomRepository.save(room));
	}

	@Override
	public void deleteRoom(final Long seq) {
		roomRepository.deleteById(seq);
	}

	@Override		//멀티룸에 참여하기
	public RoomResponse participateRoom(Long seq, Long userSeq) {
		Room room = roomRepository.findById(seq).orElseThrow(NoSuchElementException::new);
		User user = userRepository.findById(userSeq).orElseThrow(NoSuchElementException::new);
		Participant participant = new Participant(user);
		room.participateRoom(participant);
		return new RoomResponse(roomRepository.save(room));
	}

}
