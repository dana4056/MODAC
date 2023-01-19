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

import com.a608.modac.model.Article;
import com.a608.modac.model.ArticleRequest;
import com.a608.modac.service.ArticleService;

@RestController
@RequestMapping("/user")
public class ArticleController {

    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";

    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    // 게시글 작성 (POST)
    @PostMapping("/article")
    public ResponseEntity<String> createArticle(ArticleRequest.Register registDto) {
        articleService.registArticle(registDto);
        return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
    }

    // 사용자 게시글 전체 조회 (GET)
    @GetMapping("/article/list")
    public ResponseEntity<List<Article>> selectAllArticle(@RequestParam Long usersSeq) {
        return new ResponseEntity<List<Article>>(articleService.selectArticleByUsersSeq(usersSeq), HttpStatus.OK);
    }

    // 게시글 조회 (GET)
    @GetMapping("/article/")
    public ResponseEntity<Article> selectArticle(@PathVariable Long seq) {
        return new ResponseEntity<Article>(articleService.selectArticleBySeq(seq), HttpStatus.OK);
    }

    // 게시글 삭제 (DELETE)
    @DeleteMapping("/article")
    public ResponseEntity<String> removeArticle(Long seq) {
        articleService.deleteArticleBySeq(seq);
        return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
    }

}
