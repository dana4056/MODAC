package com.a608.modac.model.guest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GuestRequest {
	private Long usersSeq;
	private Long roomsSeq;
	private String invitationCode;

	public Guest toEntity(){
		return Guest.builder()
			.guestPK(GuestPK.builder()
				.usersSeq(usersSeq)
				.roomsSeq(roomsSeq)
				.build())
			.build();
	}

}
