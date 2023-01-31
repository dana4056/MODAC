package com.a608.modac.model.participant;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.a608.modac.model.room.Room;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@EqualsAndHashCode
@Embeddable
@ToString
public class ParticipantPK implements Serializable {

	@ManyToOne
	@JoinColumn(name = "rooms_seq")
	private Room room;

	@Column(name = "users_seq")
	private Long usersSeq;

	@Builder
	public ParticipantPK(final Room room, final Long usersSeq){
		this.room = room;
		this.usersSeq = usersSeq;
	}

}
