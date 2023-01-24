package com.a608.modac.model.guest;

import java.io.Serializable;

import lombok.Getter;

@Getter
public class GuestResponse implements Serializable {
	private final GuestPK guestPK;
	private final String entranceTime;

	public GuestResponse(final Guest guest){
		this.guestPK = guest.getGuestPK();
		this.entranceTime = guest.getEntranceTime();
	}

}
