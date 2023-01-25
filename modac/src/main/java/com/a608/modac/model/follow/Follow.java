package com.a608.modac.model.follow;

import lombok.*;

import javax.persistence.*;

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

    @Column(name = "from_seq")
    private Long fromSeq;

    @Column(name = "to_seq")
    private Long toSeq;

    @Column(name = "chat_rooms_seq")
    private Long chatRoomSeq;

}
