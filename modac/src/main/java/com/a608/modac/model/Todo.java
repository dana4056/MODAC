package com.a608.modac.model;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GenerationType;
import javax.persistence.Column;

@Getter
@Entity
@ToString
@Table(name = "todos")
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;

    @Column(name="users_seq")
    private Long usersSeq;

    @Column(name = "title")
    private String title;

    @Column(name="status")
    private Byte status;

    @Column(name = "total_second")
    private String totalSecond;

    @Column(name = "categories_seq")
    private Long categoriesSeq;

    @Builder
    public Todo(final Long seq, final Long usersSeq, final String title, final Byte status, final String totalSecond, final Long categoriesSeq) {
        this.seq = seq;
        this.usersSeq = usersSeq;
        this.title = title;
        this.status = status;
        this.totalSecond = totalSecond;
        this.categoriesSeq = categoriesSeq;
    }

    public Todo() {
    }

    public void updateTodo(final String title, final Byte status, final String totalSecond){
        this.title = title;
        this.status = status;
        this.totalSecond = totalSecond;
    }

}
