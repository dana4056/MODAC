package com.a608.modac.model.guest;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.a608.modac.model.room.Room;
import com.a608.modac.model.user.User;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@EqualsAndHashCode
@Embeddable
public class GuestPK implements Serializable {
	@ManyToOne
	@JoinColumn(name = "users_seq")
	private User user;

	@ManyToOne
	@JoinColumn(name = "rooms_seq")
	private Room room;

	@Builder
	public GuestPK(final User user, final Room room) {
		this.user = user;
		this.room = room;
	}

}
