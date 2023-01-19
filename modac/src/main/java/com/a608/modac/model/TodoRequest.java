package com.a608.modac.model;

import lombok.Getter;

@Getter
public class TodoRequest {
    private Long seq;
    private Long userSeq;
    private Long categorySeq;
    private String title;
    private Byte status;
    private String totalSecond;

    public Todo toEntity() {
        return Todo.builder()
                .seq(seq)
                .userSeq(userSeq)
                .categorySeq(categorySeq)
                .title(title)
                .status(status)
                .totalSecond(totalSecond)
                .build();
    }

}
