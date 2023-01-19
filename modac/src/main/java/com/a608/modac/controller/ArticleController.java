package com.a608.modac.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/api")
public class ArticleController {

    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";

    @Autowired
    private ArticleService articleService;

    // 게시글 작성 (POST)
    @PostMapping("/article")
    public ResponseEntity<String> createArticle(ArticleRequest.Regist registDto) {
        articleService.registArticle(registDto);
        return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
    }

    // 사용자 게시글 전체 조회 (GET)
    @GetMapping("/article/list")
    public ResponseEntity<List<Article>> selectAllArticle(@RequestParam String id) {
        return new ResponseEntity<List<Article>>(articleService.selectArticleByUsersId(id), HttpStatus.OK);
    }

    // 게시글 조회 (GET)
    @GetMapping("/article/")
    public ResponseEntity<Article> selectArticle(@PathVariable long seq) {
        return new ResponseEntity<Article>(articleService.selectArticleBySeq(seq), HttpStatus.OK);
    }

    // 게시글 삭제 (DELETE)
    @DeleteMapping("/article")
    public ResponseEntity<String> removeArticle(long seq) {
        articleService.deleteArticleBySeq(seq);
        return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
    }

}
