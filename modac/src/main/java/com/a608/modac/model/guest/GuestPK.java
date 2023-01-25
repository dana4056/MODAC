package com.a608.modac.model.guest;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@EqualsAndHashCode
@Embeddable
public class GuestPK implements Serializable {
	@Column(name = "users_seq")
	private Long usersSeq;
	@Column(name = "rooms_seq")
	private Long roomsSeq;

	@Builder
	public GuestPK(final Long usersSeq, final Long roomsSeq) {
		this.usersSeq = usersSeq;
		this.roomsSeq = roomsSeq;
	}

}
