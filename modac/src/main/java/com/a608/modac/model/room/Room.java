package com.a608.modac.model.room;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;

import com.a608.modac.model.chatting.ChatRoom;
import com.a608.modac.model.user.User;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@DynamicInsert
@Entity
@Table(name = "rooms")
public class Room {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long seq;
	@Column(name = "title")
	private String title;
	@Column(name = "description")
	private String description;
	@Column(name="max_size")
	private Integer maxSize;
	@Column(name = "current_size")
	private Integer currentSize;
	@Column(name="multi_theme")
	private String multiTheme;
	@Column(name="public_type")
	private Integer publicType;
	@Column(name="invitation_code")
	private String invitationCode;

	@ElementCollection
	@CollectionTable(name = "participants", joinColumns = @JoinColumn(name = "rooms_seq"))
	private List<Participant> participants = new ArrayList<Participant>();

	@ManyToOne
	@JoinColumn(name="users_seq")
	private User host;

	@OneToOne
	@JoinColumn(name = "chat_rooms_seq")
	private ChatRoom chatRoom;

	@Builder
	public Room(Long seq, String title, String description, Integer maxSize, Integer currentSize, String multiTheme, Integer publicType,
		String invitationCode,Participant participant, User host, ChatRoom chatRoom) {
		this.seq = seq;
		this.title = title;
		this.description = description;
		this.maxSize = maxSize;
		this.currentSize = currentSize;
		this.multiTheme = multiTheme;
		this.publicType = publicType;
		this.invitationCode = invitationCode;
		this.participants.add(participant);
		this.host = host;
		this.chatRoom = chatRoom;
	}

	public void updateRoom(final String title, final String description, final String multiTheme){
		this.title = title;
		this.description = description;
		this.multiTheme = multiTheme;
	}

	public void updateCurrentSize(final int cnt){
		this.currentSize += cnt;
	}

	public void participateRoom(Participant participant){
		updateCurrentSize(1);
		this.participants.add(participant);
	}

}

