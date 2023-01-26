package com.a608.modac.model.guest;

import com.a608.modac.model.room.Room;
import com.a608.modac.model.user.User;

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

	public Guest toEntity(User user, Room room){
		return Guest.builder()
			.guestPK(GuestPK.builder()
				.user(user)
				.room(room)
				.build())
			.build();
	}

}
