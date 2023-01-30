package com.a608.modac.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.a608.modac.model.article.Article;
import com.a608.modac.model.comment.Comment;
import com.a608.modac.model.user.User;
import com.a608.modac.repository.ArticleRepository;
import com.a608.modac.repository.CommentRepository;
import com.a608.modac.model.comment.CommentRequest;
import com.a608.modac.model.comment.CommentResponse;
import com.a608.modac.repository.UserRepository;

@Service
public class CommentServiceImpl implements CommentService {
	final private CommentRepository commentRepository;
	final private ArticleRepository articleRepository;
	final private UserRepository userRepository;

	public CommentServiceImpl(CommentRepository commentRepository, ArticleRepository articleRepository,
		UserRepository userRepository) {
		this.commentRepository = commentRepository;
		this.articleRepository = articleRepository;
		this.userRepository = userRepository;
	}

	// 댓글 작성
	@Override
	public CommentResponse createComment(final CommentRequest commentRequest) {

		Article article = articleRepository.findById(commentRequest.getArticlesSeq()).orElseThrow(NoSuchElementException::new);

		//댓글수 하나 올려서 저장
		article.updateCommentCount(1);
		articleRepository.save(article);

		User user = userRepository.findById(commentRequest.getUsersSeq()).orElseThrow(NoSuchElementException::new);
		Comment save = commentRepository.save(commentRequest.toEntity(article, user));
		return new CommentResponse(save);
	}

	// 댓글 삭제
	@Override
	public void deleteComment(final Long seq) {

		Comment comment = commentRepository.findById(seq).orElseThrow(NoSuchElementException::new);
		Article article = comment.getArticle();

		//댓글수 하나 내려서 저장
		article.updateCommentCount(-1);
		articleRepository.save(article);

		commentRepository.delete(comment);
	}



	// 댓글 목록조회
	@Override
	public List<CommentResponse> readCommentByArticlesSeq(final Long articlesSeq) {
		final List<Comment> findComment = commentRepository.findCommentsByArticle_Seq(articlesSeq);
		return findComment.stream().map(CommentResponse::new).collect(Collectors.toList());
	}
}
