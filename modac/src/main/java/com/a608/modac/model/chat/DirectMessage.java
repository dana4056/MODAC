package com.a608.modac.model.chat;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@RedisHash(value = "chatRedis")
public class DirectMessage implements Serializable {
	private static final long serialVersionUID = -8304525817629560393L;
	@Id
	private final String seq;
	private final String userNickName;
	@Indexed
	private final String chatRoomsSeq;
	private final String sendTime;
	private final String message;

	@Builder
	public DirectMessage(final String seq, final String chatRoomsSeq, final String userNickName, final String message,
		final String sendTime) {
		this.seq = seq;
		this.chatRoomsSeq = chatRoomsSeq;
		this.userNickName = userNickName;
		this.message = message;
		this.sendTime = sendTime;
	}

}
