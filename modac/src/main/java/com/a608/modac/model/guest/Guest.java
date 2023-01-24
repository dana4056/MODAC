package com.a608.modac.model.guest;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "guests")
@NoArgsConstructor
@DynamicInsert
public class Guest {
	@EmbeddedId
	private GuestPK guestPK;
	@Column(name = "entrance_time")
	private String entranceTime;

	@Builder
	public Guest(final GuestPK guestPK, final String entranceTime) {
		this.guestPK = guestPK;
		this.entranceTime = entranceTime;
	}

}
