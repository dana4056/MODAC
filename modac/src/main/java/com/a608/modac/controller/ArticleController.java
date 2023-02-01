package com.a608.modac.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.a608.modac.model.article.ArticleRequest;
import com.a608.modac.model.article.ArticleResponse;
import com.a608.modac.model.article.LikeRequest;
import com.a608.modac.service.ArticleService;

@CrossOrigin(origins = { "*" })
@RestController
@RequestMapping("/articles")
public class ArticleController {

    // private static final String SUCCESS = "success";
    // private static final String FAIL = "fail";

    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    // 게시글 작성 (POST)
    @PostMapping
    public ResponseEntity<?> createArticle(@RequestBody final ArticleRequest articleRequest) {
        return new ResponseEntity<ArticleResponse.ArticleInfo>(articleService.createArticle(articleRequest),
            HttpStatus.OK);
    }

    // 사용자 게시글 전체 조회 (GET)
    @GetMapping
    public ResponseEntity<?> selectAllArticle(@RequestParam("user") final Long usersSeq,
        @RequestParam("offset") final Integer offset, @RequestParam("limit") final Integer limit) {
        return new ResponseEntity<ArticleResponse>(articleService.readArticlesByUsersSeq(usersSeq, offset, limit),
            HttpStatus.OK);
    }

    // 게시글 조회 (GET)
    @GetMapping("/{seq}")
    public ResponseEntity<?> selectArticle(@PathVariable("seq") final Long seq) {
        return new ResponseEntity<ArticleResponse.ArticleInfo>(articleService.readArticleBySeq(seq), HttpStatus.OK);
    }

    // 게시글 조회수 올리기
    @PostMapping("/{seq}/view")
    public ResponseEntity<?> upViewCount(@PathVariable("seq") final Long seq){
        articleService.upViewCount(seq);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // 게시글 삭제 (DELETE)
    @DeleteMapping("/{seq}")
    public ResponseEntity<?> removeArticle(@PathVariable("seq") final Long seq) {
        articleService.deleteArticleBySeq(seq);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // 팔로잉 게시글 목록 조회 (GET)
    @GetMapping("/following")
    public ResponseEntity<?> selectArticlesByFollowing(@RequestParam("user") final Long usersSeq,
        @RequestParam("offset") final Integer offset, @RequestParam("limit") final Integer limit) {
        return new ResponseEntity<ArticleResponse>(articleService.readArticlesByFollowing(usersSeq, offset, limit),
            HttpStatus.OK);
    }

    @PostMapping("/like")
    // 게시글-유저 좋아요 관계 추가
    public ResponseEntity<?> createLike(@RequestBody LikeRequest likeRequest){
        articleService.createLike(likeRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/like")
    // 게시글-유저 좋아요 관계 삭제
    public ResponseEntity<?> deleteLike(@RequestBody LikeRequest likeRequest){
        articleService.deleteLike(likeRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/like")
    // 게시글-유저 좋아요 관계 개수 조회
    public ResponseEntity<?> countLike(@RequestBody LikeRequest likeRequest){
        return new ResponseEntity<Boolean>(articleService.countLike(likeRequest), HttpStatus.OK);
    }
}
