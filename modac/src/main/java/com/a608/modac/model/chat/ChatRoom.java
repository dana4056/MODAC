package com.a608.modac.model.chat;

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
@Entity
@NoArgsConstructor
@Table(name = "chat_rooms")
public class ChatRoom {
    // stomp를 통해 pub/sub를 사용하면 구독자 관리가 알아서 된다.
    // 따라서 세션을 따로 관리하는 코드를 작성할 필요가 없다.
    // 메시지를 다른 세션의 클라이언트에게 발송하는 것도 구현할 필요가 없다.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;

    @Column(name = "last_message_seq")
    private String lastMessageSeq;

    @Column(name = "last_message_time")
    private String lastMessageTime;

    @Builder
    public ChatRoom(final Long seq, final String lastMessageSeq, final String lastMessageTime) {
        this.seq = seq;
        this.lastMessageSeq = lastMessageSeq;
        this.lastMessageTime = lastMessageTime;
    }

    public void updateChatRoom(final String lastMessageSeq, final String lastMessageTime){
        System.out.println("++++++++++++++++++++++++++++업댓"+lastMessageSeq +lastMessageTime);
        this.lastMessageSeq = lastMessageSeq;
        this.lastMessageTime = lastMessageTime;
    }

}
