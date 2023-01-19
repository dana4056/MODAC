package com.a608.modac.service;

import com.a608.modac.model.Todo;
import com.a608.modac.model.TodoRepository;
import com.a608.modac.model.TodoRequest;
import com.a608.modac.model.TodoResponse;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TodoService {
	private final TodoRepository todoRepository;

	public TodoService(TodoRepository todoRepository) {
		this.todoRepository = todoRepository;
	}

	public void deleteTodo(final Long seq) {
		todoRepository.deleteById(seq);
	} // todo 삭제

	public List<TodoResponse> findTodosByUserSeq(final Long userSeq) {
		final List<Todo> findTodos = todoRepository.findTodosByUserSeq(userSeq);

		return findTodos.stream().map(TodoResponse::new).collect(Collectors.toList());
	} // todo 목록조회

	public void saveTodo(final TodoRequest todoRequest) {
		final Todo save = todoRepository.save(todoRequest.toEntity());
		final Long seq = save.getSeq();// 저장한 Todo 번호 반환.
	} // todo 작성.

	public void updateTodo(final TodoRequest todoRequest){
		final Optional<Todo> findTodo = todoRepository.findById(todoRequest.getSeq());
		System.out.println(findTodo.get().toString());
		findTodo.ifPresent(todo -> todo.updateTodo(todoRequest.getTitle(), todoRequest.getStatus(), todoRequest.getTotalSecond()));

		final Long updateTodoSeq = todoRequest.getSeq();
	} // todo 수정 (내용, 상태, 시간)

}