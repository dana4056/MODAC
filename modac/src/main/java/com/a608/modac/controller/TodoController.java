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
@RequestMapping("/todo")
public class TodoController {

	private final TodoService todoService;

	public TodoController(final TodoService todoService) {
		this.todoService = todoService;
	}

	@GetMapping("/list")
	public ResponseEntity<List<TodoResponse>> findTodosByUserSeq(@RequestParam final Long userSeq){
		final List<TodoResponse> todosByUserSeq = todoService.findTodosByUsersSeq(userSeq);

		return new ResponseEntity<>(todosByUserSeq, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Void> saveTodo(@RequestBody final TodoRequest todoRequest){
		todoService.saveTodo(todoRequest);

		return new ResponseEntity<>(HttpStatus.OK);
	}

	@DeleteMapping("/{seq}")
	public ResponseEntity<Void> deleteTodo(@PathVariable("seq") final Long seq){
		System.out.println("in");
		todoService.deleteTodo(seq);

		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PutMapping
	public ResponseEntity<List<TodoResponse>> updateTodo(@RequestBody TodoRequest todoRequest){
		todoService.updateTodo(todoRequest);
		final List<TodoResponse> todosByUserSeq = todoService.findTodosByUsersSeq(todoRequest.getSeq());

		return new ResponseEntity<>(todosByUserSeq, HttpStatus.OK);
	}

}