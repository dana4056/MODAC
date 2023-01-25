package com.a608.modac.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.a608.modac.model.guest.Guest;
import com.a608.modac.model.guest.GuestPK;
import com.a608.modac.repository.GuestRepository;
import com.a608.modac.model.guest.GuestRequest;
import com.a608.modac.model.guest.GuestResponse;

@Service
public class GuestServiceImpl implements GuestService {

	private final GuestRepository guestRepository;

	public GuestServiceImpl(final GuestRepository guestRepository) {
		this.guestRepository = guestRepository;
	}

	@Override
	public void deleteGuestInfo(final GuestPK guestPK) {
		guestRepository.deleteById(guestPK);
	}

	@Override
	public List<GuestResponse> findAllMyRooms(final Long usersSeq) {
		final List<Guest> guestByUsersSeq = guestRepository.findGuestByGuestPK_UsersSeq(usersSeq);

		return guestByUsersSeq.stream().map(GuestResponse::new).collect(Collectors.toList());
	}

	@Override
	public GuestResponse findMyRoom(final GuestPK guestPK) {
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
		guestRepository.save(guestRequest.toEntity());
	}

}
