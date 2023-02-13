package com.a608.modac.model.todo;

import lombok.Builder;
import lombok.Getter;

@Getter
public class TodoResponse {
    private Long seq;
    private String title;
    private Byte status;
    private String totalSecond;
    private String categoriesName;
    @Builder
    public TodoResponse(Todo todo){
        this.seq = todo.getSeq();
        this.title = todo.getTitle();
        this.status = todo.getStatus();
        this.totalSecond = todo.getTotalSecond();
        this.categoriesName = todo.getCategory().getName();
    }

}
