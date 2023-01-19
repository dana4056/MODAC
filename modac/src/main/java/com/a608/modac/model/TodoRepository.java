package com.a608.modac.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {
    // TodoResponse findTodoUserSeq(final Long userSeq);

    List<Todo> findTodosByUserSeq(final Long userSeq);

}
