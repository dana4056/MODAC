package com.a608.modac.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.a608.modac.model.guest.Guest;
import com.a608.modac.model.guest.GuestPK;
import com.a608.modac.model.room.Room;
import com.a608.modac.model.user.User;
import com.a608.modac.repository.GuestRepository;
import com.a608.modac.model.guest.GuestRequest;
import com.a608.modac.model.guest.GuestResponse;
import com.a608.modac.repository.RoomRepository;
import com.a608.modac.repository.UserRepository;

@Service
public class GuestServiceImpl implements GuestService {

	private final GuestRepository guestRepository;
	private final UserRepository userRepository;
	private final RoomRepository roomRepository;


	public GuestServiceImpl(final GuestRepository guestRepository, UserRepository userRepository,
		RoomRepository roomRepository) {
		this.guestRepository = guestRepository;
		this.userRepository = userRepository;
		this.roomRepository = roomRepository;
	}

	@Override
	public void deleteGuestInfo(final Long usersSeq, final Long roomsSeq) {
		User user = userRepository.findById(usersSeq).orElseThrow(NoSuchElementException::new);
		Room room = roomRepository.findById(roomsSeq).orElseThrow(NoSuchElementException::new);

		final GuestPK guestPK = GuestPK.builder().user(user).room(room).build();

		guestRepository.deleteById(guestPK);
	}

	@Override
	public List<GuestResponse> findAllMyRooms(final Long usersSeq) {
		final List<Guest> guestByUsersSeq = guestRepository.findGuestsByGuestPK_User_Seq(usersSeq);

		return guestByUsersSeq.stream().map(GuestResponse::new).collect(Collectors.toList());
	}

	@Override
	public GuestResponse findMyRoom(final Long usersSeq, final Long roomsSeq) {

		User user = userRepository.findById(usersSeq).orElseThrow(NoSuchElementException::new);
		Room room = roomRepository.findById(roomsSeq).orElseThrow(NoSuchElementException::new);

		final GuestPK guestPK = GuestPK.builder().user(user).room(room).build();
		final Optional<Guest> findMyRoom = guestRepository.findById(guestPK);
		GuestResponse guestResponse = null;

		if (findMyRoom.isPresent()) {
			guestResponse = new GuestResponse(findMyRoom.get());
		}

		return guestResponse;
	}

	@Override
	public void addGuestInfo(final GuestRequest guestRequest) {
		final String invitationCode = guestRequest.getInvitationCode(); // 유저가 입력한 비공개방 초대코드

		// 비공개 방 초대코드와 비교하여 게스트 입장여부 결정
		User user = userRepository.findById(guestRequest.getUsersSeq()).orElseThrow(NoSuchElementException::new);
		Room room = roomRepository.findById(guestRequest.getRoomsSeq()).orElseThrow(NoSuchElementException::new);
		guestRepository.save(guestRequest.toEntity(user, room));

	}

}
