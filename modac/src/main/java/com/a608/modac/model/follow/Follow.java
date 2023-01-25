package com.a608.modac.model.follow;

import lombok.*;

import javax.persistence.*;

import com.a608.modac.model.chatting.ChatRoom;
import com.a608.modac.model.user.User;

@Getter
@Entity
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "followings")
public class Follow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;

    @ManyToOne
    @JoinColumn(name = "from_seq")
    private User fromUser;

    @ManyToOne
    @JoinColumn(name = "to_seq")
    private User toUser;

    @ManyToOne
    @JoinColumn(name = "chat_rooms_seq")
    private ChatRoom chatRoom;

}
