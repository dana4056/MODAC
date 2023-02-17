package com.a608.modac.repository;

import com.a608.modac.model.todo.Todo;
import com.a608.modac.model.user.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {
    List<Todo> findTodosByUser_Seq(final Long usersSeq);
}
