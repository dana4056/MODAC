package com.a608.modac.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.a608.modac.model.article.Article;
import com.a608.modac.model.article.ArticleRequest;
import com.a608.modac.model.article.ArticleResponse;
import com.a608.modac.model.todo.TodoResponse;
import com.a608.modac.service.ArticleService;
import com.a608.modac.service.TodoService;

@RestController
@RequestMapping("/article")
public class ArticleController {

    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";

    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    // 게시글 작성 (POST)
    @PostMapping
    public ResponseEntity<String> createArticle(@RequestBody final ArticleRequest articleRequest) {
        articleService.createArticle(articleRequest);
        return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
    }

    // 사용자 게시글 전체 조회 (GET)
    @GetMapping("/list")
    public ResponseEntity<List<ArticleResponse>> selectAllArticle(@RequestParam final Long usersSeq) {
        return new ResponseEntity<List<ArticleResponse>>(articleService.readArticleByUsersSeq(usersSeq), HttpStatus.OK);
    }

    // 게시글 조회 (GET)
    @GetMapping
    public ResponseEntity<ArticleResponse> selectArticle(@RequestParam final Long seq) {
        return new ResponseEntity<ArticleResponse>(articleService.readArticleBySeq(seq), HttpStatus.OK);
    }

    // 게시글 삭제 (DELETE)
    @DeleteMapping
    public ResponseEntity<String> removeArticle(@RequestParam final Long seq) {
        articleService.deleteArticleBySeq(seq);
        return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
    }
}
