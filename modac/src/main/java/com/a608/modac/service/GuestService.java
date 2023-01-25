package com.a608.modac.service;

import java.util.List;

import com.a608.modac.model.guest.GuestPK;
import com.a608.modac.model.guest.GuestRequest;
import com.a608.modac.model.guest.GuestResponse;

public interface GuestService {
	void deleteGuestInfo(final GuestPK guestPK); // 비공개 멀티룸 입장정보 삭제
	List<GuestResponse> findAllMyRooms(final Long usersSeq); // 나의 비공개방 리스트 보기.
	GuestResponse findMyRoom(final GuestPK guestPK); // 나의 비공개방(하나) 조회
	void addGuestInfo(final GuestRequest guestRequest);

}
