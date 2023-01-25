package com.a608.modac.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.a608.modac.model.comment.Comment;
import com.a608.modac.model.comment.CommentRepository;
import com.a608.modac.model.comment.CommentRequest;
import com.a608.modac.model.comment.CommentResponse;

@Service
public class CommentServiceImpl implements CommentService {
	private CommentRepository commentRepository;

	public CommentServiceImpl(CommentRepository commentRepository) {
		this.commentRepository = commentRepository;
	}

	// 댓글 작성
	@Override
	public void createComment(final CommentRequest commentRequest) {
		commentRepository.save(commentRequest.toEntity());
	}

	// 댓글 삭제
	@Override
	public void deleteComment(final Long seq) {
		commentRepository.deleteById(seq);
	}

	// 댓글 목록조회
	@Override
	public List<CommentResponse> readCommentByArticlesSeq(final Long articlesSeq) {
		final List<Comment> findComment = commentRepository.findCommentsByArticlesSeq(articlesSeq);
		return findComment.stream().map(CommentResponse::new).collect(Collectors.toList());
	}
}
