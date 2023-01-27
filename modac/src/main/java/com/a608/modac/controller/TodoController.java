package com.a608.modac.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.a608.modac.model.todo.TodoRequest;
import com.a608.modac.model.todo.TodoResponse;
import com.a608.modac.service.TodoService;

@CrossOrigin(origins = { "*" })
@RestController
@RequestMapping("/todos")
public class TodoController {

	private final TodoService todoService;

	public TodoController(final TodoService todoService) {
		this.todoService = todoService;
	}

	@PostMapping					// Todo 등록
	public ResponseEntity<?> saveTodo(@RequestBody final TodoRequest todoRequest){
		TodoResponse todoResponse = todoService.saveTodo(todoRequest);

		return new ResponseEntity<TodoResponse>(todoResponse, HttpStatus.OK);
	}

	@GetMapping	("/{seq}")					// Todo 한개 조회
	public ResponseEntity<TodoResponse> findTodo(@PathVariable final Long seq){
		TodoResponse todo = todoService.findTodo(seq);
		return new ResponseEntity<TodoResponse>(todo, HttpStatus.OK);
	}
	@PutMapping("/{seq}")					// Todo 수정
	public ResponseEntity<TodoResponse> updateTodo(@PathVariable final Long seq, @RequestBody TodoRequest todoRequest){
		TodoResponse todoResponse = todoService.updateTodo(seq, todoRequest);
		return new ResponseEntity<TodoResponse>(todoResponse, HttpStatus.OK);
	}
	@DeleteMapping("/{seq}")
	public ResponseEntity<Void> deleteTodo(@PathVariable("seq") final Long seq){
		System.out.println("in");
		todoService.deleteTodo(seq);

		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<List<TodoResponse>> findTodosByUserSeq(@RequestParam("usersSeq") final Long userSeq){
		final List<TodoResponse> todosByUserSeq = todoService.findTodosByUsersSeq(userSeq);
		return new ResponseEntity<>(todosByUserSeq, HttpStatus.OK);
	}


}