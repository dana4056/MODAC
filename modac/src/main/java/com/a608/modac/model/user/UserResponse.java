package com.a608.modac.model.user;

import lombok.Getter;

@Getter
public class UserResponse {
    private Long seq;
    private String title;
    private Byte status;
    private String totalSecond;
    private Long categoriesSeq;

    public UserResponse(User todo){
        this.seq = todo.getSeq();
        this.title = todo.getTitle();
        this.status = todo.getStatus();
        this.totalSecond = todo.getTotalSecond();
        this.categoriesSeq = todo.getCategoriesSeq();
    }

}
