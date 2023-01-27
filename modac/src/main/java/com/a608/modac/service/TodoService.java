package com.a608.modac.service;

import com.a608.modac.model.todo.TodoRequest;
import com.a608.modac.model.todo.TodoResponse;

import java.util.List;

public interface TodoService {
	TodoResponse saveTodo(final TodoRequest todoRequest); 				// todo 작성.
	TodoResponse findTodo(final Long seq);								// todo 조회
	TodoResponse updateTodo(final Long seq, final TodoRequest todoRequest);		// todo 수정 (내용, 상태, 시간)
	void deleteTodo(final Long seq); 									// todo 삭제

	List<TodoResponse> findTodosByUsersSeq(final Long usersSeq); 		// todo 목록조회



}