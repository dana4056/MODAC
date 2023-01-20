package com.a608.modac.model.user;

import lombok.Getter;

@Getter
public class UserRequest {
    private Long seq;
    private Long usersSeq;
    private Long categoriesSeq;
    private String title;
    private Byte status;
    private String totalSecond;

    public User toEntity() {
        return User.builder()
                .seq(seq)
                .usersSeq(usersSeq)
                .categoriesSeq(categoriesSeq)
                .title(title)
                .status(status)
                .totalSecond(totalSecond)
                .build();
    }

}
