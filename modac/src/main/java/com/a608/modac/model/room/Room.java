package com.a608.modac.model.room;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "rooms")
public class Room {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long seq;
	private String title;
	private String description;
	@Column(name="max_size")
	private Integer maxSize;
	@Column(name="multi_theme")
	private String multiTheme;
	@Column(name="public_type")
	private Integer publicType;
	@Column(name="invitation_code")
	private String invitationCode;
	@Column(name="users_seq")
	private Long usersSeq;

	@Builder
	public Room(Long seq, String title, String description, Integer maxSize, String multiTheme, Integer publicType,
		String invitationCode, Long usersSeq) {
		this.seq = seq;
		this.title = title;
		this.description = description;
		this.maxSize = maxSize;
		this.multiTheme = multiTheme;
		this.publicType = publicType;
		this.invitationCode = invitationCode;
		this.usersSeq = usersSeq;
	}

	public void updateRoom(final String title, final String description, final String multiTheme){
		this.title = title;
		this.description = description;
		this.multiTheme = multiTheme;
	}

}
