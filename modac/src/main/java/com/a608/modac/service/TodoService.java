package com.a608.modac.service;

import com.a608.modac.model.todo.TodoRequest;
import com.a608.modac.model.todo.TodoResponse;

import java.util.List;

public interface TodoService {
	void deleteTodo(final Long seq); // todo 삭제

	List<TodoResponse> findTodosByUsersSeq(final Long usersSeq); // todo 목록조회

	void saveTodo(final TodoRequest todoRequest); // todo 작성.

	void updateTodo(final TodoRequest todoRequest);// todo 수정 (내용, 상태, 시간)

}