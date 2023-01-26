package com.a608.modac.model.article;

import com.a608.modac.model.todo.Todo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
public class ArticleRequest {
	private Long usersSeq;
	private Long todosSeq;
	private Byte publicType;
	private String filepath;

	public ArticleRequest(Long usersSeq, Long todosSeq, Byte publicType, String filepath) {
		this.usersSeq = usersSeq;
		this.todosSeq = todosSeq;
		this.publicType = publicType;
		this.filepath = filepath;
	}

	public Article toEntity(Todo todo){
		return Article.builder()
			.title(todo.getTitle())
			.filepath(filepath)
			.publicType(publicType)
			.totalSecond(todo.getTotalSecond())
			.user(todo.getUser())
			.category(todo.getCategory())
			.build();
	}
}
