package com.a608.modac.model.article;

import java.time.LocalDateTime;

import com.a608.modac.model.todo.Todo;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ArticleRequest {
	private Long usersSeq;
	private Long todosSeq;
	private Byte publicType;
	private String content;

	public ArticleRequest(Long usersSeq, Long todosSeq, Byte publicType, String content) {
		this.usersSeq = usersSeq;
		this.todosSeq = todosSeq;
		this.publicType = publicType;
		this.content = content;
	}

	public Article toEntity(Todo todo, String filepath){
		return Article.builder()
			.title(todo.getTitle())
			.filepath(filepath)
			.publicType(publicType)
			.totalSecond(todo.getTotalSecond())
			.user(todo.getUser())
			.category(todo.getCategory())
			.registeredTime(LocalDateTime.now())
			.totalSecond("0")
			.build();
	}
}
