package com.a608.modac.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.a608.modac.model.todo.Todo;
import com.a608.modac.repository.TodoRepository;
import com.a608.modac.model.todo.TodoRequest;
import com.a608.modac.model.todo.TodoResponse;

@Service
public class TodoServiceImpl implements TodoService{
	private final TodoRepository todoRepository;

	public TodoServiceImpl(TodoRepository todoRepository) {
		this.todoRepository = todoRepository;
	}

	public void deleteTodo(final Long seq) {
		todoRepository.deleteById(seq);
	} // todo 삭제

	@Override
	public List<TodoResponse> findTodosByUsersSeq(final Long usersSeq) {
		final List<Todo> findTodos = todoRepository.findTodosByUsersSeq(usersSeq);

		return findTodos.stream().map(TodoResponse::new).collect(Collectors.toList());
	} // todo 목록조회

	@Override
	public void saveTodo(final TodoRequest todoRequest) {

		todoRepository.save(todoRequest.toEntity());

		final Todo save = todoRepository.save(todoRequest.toEntity());
		final Long seq = save.getSeq();// 저장한 Todo 번호 반환.

	} // todo 작성.

	@Override
	public void updateTodo(final TodoRequest todoRequest){
		final Optional<Todo> findTodo = todoRepository.findById(todoRequest.getSeq());
		findTodo.ifPresent(todo -> todo.updateTodo(todoRequest.getTitle(), todoRequest.getStatus(), todoRequest.getTotalSecond()));
		todoRepository.save(findTodo.get());
	} // todo 수정 (내용, 상태, 시간)

}