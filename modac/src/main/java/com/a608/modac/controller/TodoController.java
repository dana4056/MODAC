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

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;

@Api(tags = "Todo Controller", description = "개인 To do 리스트 관련 API")
@CrossOrigin(origins = { "*" })
@RestController
@RequestMapping("/todos")
public class TodoController {

	private final TodoService todoService;

	public TodoController(final TodoService todoService) {
		this.todoService = todoService;
	}

	@Operation(summary = "To do 등록", description = "<b>[categoriesName 종류]</b><br>"
		+ "알고리즘<br>"
		+ "CS<br>"
		+ "개발<br>"
		+ "기획<br>"
		+ "기타<br>")
	@PostMapping					// Todo 등록
	public ResponseEntity<?> saveTodo(@RequestBody final TodoRequest todoRequest){
		TodoResponse todoResponse = todoService.saveTodo(todoRequest);

		return new ResponseEntity<TodoResponse>(todoResponse, HttpStatus.CREATED);
	}

	@Operation(summary = "특정 To do 정보 조회", description = "사용자가 어떤 작업을 진행하고 있는지 조회하기 위해 To do 한 개에 대해 조회")
	@GetMapping	("/{seq}")					// Todo 한개 조회
	public ResponseEntity<TodoResponse> findTodo(@PathVariable final Long seq){
		TodoResponse todo = todoService.findTodo(seq);
		return new ResponseEntity<TodoResponse>(todo, HttpStatus.OK);
	}

	@Operation(summary = "To do 수정", description = "제목, 상태, 총시간, 카테고리 이름 수정 가능<br>"
		+ "넷 중 일부분만 수정할 때에도 모든 요소 다 보내야 함<br><br>"
		+ "<b>[status]</b><br>"
		+ "0: 중지상태<br>"
		+ "1: 재생상태<br>"
		+ "2: to do 완료 상태<br>")
	@PutMapping("/{seq}")					// Todo 수정
	public ResponseEntity<TodoResponse> updateTodo(@PathVariable final Long seq, @RequestBody TodoRequest todoRequest){
		TodoResponse todoResponse = todoService.updateTodo(seq, todoRequest);
		return new ResponseEntity<TodoResponse>(todoResponse, HttpStatus.CREATED);
	}

	@Operation(summary = "To do 삭제", description = "특정 to do 삭제")
	@DeleteMapping("/{seq}")
	public ResponseEntity<Void> deleteTodo(@PathVariable("seq") final Long seq){
		System.out.println("in");
		todoService.deleteTodo(seq);

		return new ResponseEntity<>(HttpStatus.OK);
	}

	@Operation(summary = "To do 목록 조회", description = "사용자(userSeq)가 작성한 to do 목록 조회")
	@GetMapping
	public ResponseEntity<List<TodoResponse>> findTodosByUserSeq(@RequestParam("user") final Long userSeq){
		final List<TodoResponse> todosByUserSeq = todoService.findTodosByUsersSeq(userSeq);
		return new ResponseEntity<>(todosByUserSeq, HttpStatus.OK);
	}


}