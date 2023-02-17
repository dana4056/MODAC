package com.a608.modac.model.chat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.a608.modac.model.user.User;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@Entity
@ToString
@NoArgsConstructor
@Table(name = "chat_messages")
public class ChatMessage {
	@Id
	private String seq;

	@ManyToOne
	@JoinColumn(name = "users_seq")
	private User user; // 채팅 메시지를 보낸사람.

	@ManyToOne
	@JoinColumn(name = "chat_rooms_seq")
	private ChatRoom chatRoom; // 채팅 방 번호.

	@Column(name = "send_time")
	private String sendTime;

	@Column(name="message")
	private String message;

	@Builder
	public ChatMessage(final String seq, final User user, final ChatRoom chatRoom, final String sendTime,
		final String message) {
		this.seq = seq;
		this.user = user;
		this.chatRoom = chatRoom;
		this.sendTime = sendTime;
		this.message = message;
	}

}
