package com.a608.modac.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.a608.modac.model.article.Article;
import com.a608.modac.model.article.ArticleRequest;
import com.a608.modac.model.todo.TodoResponse;
import com.a608.modac.service.ArticleService;
import com.a608.modac.service.TodoService;

@RestController
@RequestMapping("/article")
public class ArticleController {

    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";

    private final ArticleService articleService;
    private final TodoService todoService;

    public ArticleController(ArticleService articleService, TodoService todoService) {
        this.articleService = articleService;
        this.todoService = todoService;
    }

    // 게시글 작성 (POST)
    @PostMapping("/article")
    public ResponseEntity<String> createArticle(ArticleRequest.createArticleRequest registerDto) {
        // usersSeq로 Todo List 조회
        List<TodoResponse> todoList = todoService.findTodosByUsersSeq(registerDto.getUsersSeq());

        // todosSeq와 일치하는 todo를 전달
        for(TodoResponse todoResponse : todoList) {
            if(todoResponse.getSeq() == registerDto.getTodosSeq()){
                articleService.createArticle(registerDto, todoResponse);
                return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
            }
        }

        // 일치하는 todo가 없으면 실패
        return new ResponseEntity<String>(FAIL, HttpStatus.OK);
    }

    // 사용자 게시글 전체 조회 (GET)
    @GetMapping("/article/list")
    public ResponseEntity<List<Article>> selectAllArticle(@RequestParam Long usersSeq) {
        return new ResponseEntity<List<Article>>(articleService.readArticleByUsersSeq(usersSeq), HttpStatus.OK);
    }

    // 게시글 조회 (GET)
    @GetMapping("/article/")
    public ResponseEntity<Article> selectArticle(@PathVariable Long seq) {
        return new ResponseEntity<Article>(articleService.readArticleBySeq(seq), HttpStatus.OK);
    }

    // 게시글 삭제 (DELETE)
    @DeleteMapping("/article")
    public ResponseEntity<String> removeArticle(Long seq) {
        articleService.deleteArticleBySeq(seq);
        return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
    }

}
