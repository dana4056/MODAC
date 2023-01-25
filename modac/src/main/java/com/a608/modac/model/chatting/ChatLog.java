package com.a608.modac.model.chatting;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.a608.modac.model.user.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@Entity
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ChatLog {

	@Id
	private Long key;

	@ManyToOne
	@JoinColumn(name = "users_seq")
	private User user;

	@ManyToOne
	@JoinColumn(name = "chat_rooms_seq")
	private ChatRoom chatRoom;

	private String sendTime;
}
