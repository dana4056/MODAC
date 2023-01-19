package com.a608.modac.service;

import com.a608.modac.model.Todo;
import com.a608.modac.model.TodoRepository;
import com.a608.modac.model.TodoRequest;
import com.a608.modac.model.TodoResponse;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public interface TodoService {
	void deleteTodo(final Long seq); // todo 삭제

	List<TodoResponse> findTodosByUsersSeq(final Long usersSeq); // todo 목록조회

	void saveTodo(final TodoRequest todoRequest); // todo 작성.

	void updateTodo(final TodoRequest todoRequest);// todo 수정 (내용, 상태, 시간)

}