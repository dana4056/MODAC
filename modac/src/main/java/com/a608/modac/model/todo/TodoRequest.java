package com.a608.modac.model.todo;

import com.a608.modac.model.category.Category;
import com.a608.modac.model.user.User;

import lombok.Getter;

@Getter
public class TodoRequest {
    private Long seq;
    private Long usersSeq;
    private String categoriesName;
    private String title;
    private Byte status;
    private String totalSecond;

    public Todo toEntity(User user, Category category) {
        return Todo.builder()
                .seq(seq)
                .user(user)
                .category(category)
                .title(title)
                .status(status)
                .totalSecond(totalSecond)
                .build();
    }

}
