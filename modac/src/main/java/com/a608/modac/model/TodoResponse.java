package com.a608.modac.model;

import lombok.Getter;

@Getter
public class TodoResponse {
    private Long seq;
    private String title;
    private Byte status;
    private String totalSecond;
    private Long categorySeq;

    public TodoResponse(Todo todo){
        this.seq = todo.getSeq();
        this.title = todo.getTitle();
        this.status = todo.getStatus();
        this.totalSecond = todo.getTotalSecond();
        this.categorySeq = todo.getCategorySeq();
    }

}
