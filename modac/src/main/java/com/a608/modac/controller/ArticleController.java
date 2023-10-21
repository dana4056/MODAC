package com.a608.modac.controller;

import javax.servlet.http.HttpServletRequest;
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
import com.a608.modac.model.article.StatisticsResponse;
import com.a608.modac.service.ArticleService;

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;

@Api(tags = "Article Controller", description = "게시글 관련 API")
@CrossOrigin(origins = {"*"})
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
    @Operation(summary = "게시글 작성")
    @PostMapping
    public ResponseEntity<?> createArticle(@RequestBody final ArticleRequest articleRequest) {
        return new ResponseEntity<ArticleResponse.ArticleInfo>(articleService.createArticle(articleRequest),
                HttpStatus.CREATED);
    }

    // 사용자 게시글 전체 조회 (GET)
    @Operation(summary = "사용자 게시글 전체 조회", description = "특정 사용자(user)가 작성한 게시글 목록 조회")
    @GetMapping
    public ResponseEntity<?> selectAllArticle(@RequestParam("user") final Long usersSeq,
                                              @RequestParam("offset") final Integer offset, @RequestParam("limit") final Integer limit) {
        return new ResponseEntity<ArticleResponse>(articleService.readArticlesByUsersSeq(usersSeq, offset, limit),
                HttpStatus.OK);
    }

    // 게시글 조회 (GET)
    @Operation(summary = "게시글 조회", description = "특정 게시글(seq) 조회")
    @GetMapping("/{seq}")
    public ResponseEntity<?> selectArticle(@PathVariable("seq") final Long seq) {
        return new ResponseEntity<ArticleResponse.ArticleInfo>(articleService.readArticleBySeq(seq), HttpStatus.OK);
    }

    // 게시글 조회수 올리기
    @Operation(summary = "게시글 조회수 올리기", description = "특정 게시글(seq) 조회수 올리기")
    @PostMapping("/{seq}/view/{usersSeq}")
    public ResponseEntity<?> upViewCount(@PathVariable("seq") final Long seq, @PathVariable("usersSeq") Long usersSeq) {
        articleService.upViewCount(seq, usersSeq);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    // 게시글 삭제 (DELETE)
    @Operation(summary = "게시글 삭제", description = "특정 게시글(seq) 삭제하기")
    @DeleteMapping("/{seq}")
    public ResponseEntity<?> removeArticle(@PathVariable("seq") final Long seq) {
        articleService.deleteArticleBySeq(seq);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // 팔로잉 게시글 목록 조회 (GET)
    @Operation(summary = "팔로잉 게시글 목록 조회", description = "특정 사용자(user)가 팔로잉하고 있는 사람들이 작성한 게시물 목록 조회")
    @GetMapping("/following")
    public ResponseEntity<?> selectArticlesByFollowing(@RequestParam("user") final Long usersSeq,
                                                       @RequestParam("offset") final Integer offset, @RequestParam("limit") final Integer limit) {
        return new ResponseEntity<ArticleResponse>(articleService.readArticlesByFollowing(usersSeq, offset, limit),
                HttpStatus.OK);
    }

    // 게시글-유저 좋아요 관계 추가
    @PostMapping("/like")
    @Operation(summary = "게시글 좋아요", description = "특정 사용자(usersSeq)가 특정 게시물(articlesSeq) 좋아요")
    public ResponseEntity<?> createLike(@RequestBody LikeRequest likeRequest) {
        articleService.createLike(likeRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    // 게시글-유저 좋아요 관계 삭제
    @Operation(summary = "게시글 좋아요 취소", description = "특정 사용자(usersSeq)가 특정 게시물(articlesSeq) 좋아요 취소")
    @DeleteMapping("/like")
    public ResponseEntity<?> deleteLike(@RequestParam("articlesSeq") Long articlesSeq, @RequestParam("usersSeq") Long usersSeq) {
        articleService.deleteLike(articlesSeq, usersSeq);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // 게시글-유저 좋아요 관계 여부 조회
    @Operation(summary = "게시글 좋아요 여부 조회", description = "특정 사용자(usersSeq)가 특정 게시물(articlesSeq) 좋아요 했는지 여부 (true/false)")
    @GetMapping("/like")
    public ResponseEntity<?> countLike(@RequestParam("articlesSeq") Long articlesSeq, @RequestParam("usersSeq") Long usersSeq) {
        return new ResponseEntity<Boolean>(articleService.countLike(articlesSeq, usersSeq), HttpStatus.OK);
    }

    // 최근 일주일간 요일별, 날짜별 통계 조회
    @Operation(summary = "최근 일주일 간 작성한 게시글 정보 조회", description = "특정 사용자(user)가 최근 일주일간 작성한 글 정보 -> 요일 별 작성 개수, 카테고리 별 개수 정보 제공")
    @GetMapping("/statistics")
    public ResponseEntity<?> findStats(@RequestParam("user") Long usersSeq) {
        return new ResponseEntity<StatisticsResponse>(articleService.findStats(usersSeq), HttpStatus.OK);
    }

}

