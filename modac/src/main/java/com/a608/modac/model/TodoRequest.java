package com.a608.modac.model;

import lombok.Getter;

@Getter
public class TodoRequest {
    private Long seq;
    private Long usersSeq;
    private Long categoriesSeq;
    private String title;
    private Byte status;
    private String totalSecond;

    public Todo toEntity() {
        return Todo.builder()
                .seq(seq)
                .usersSeq(usersSeq)
                .categoriesSeq(categoriesSeq)
                .title(title)
                .status(status)
                .totalSecond(totalSecond)
                .build();
    }

}
