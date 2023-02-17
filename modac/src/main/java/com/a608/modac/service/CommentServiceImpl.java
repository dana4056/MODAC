package com.a608.modac.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.a608.modac.model.article.Article;
import com.a608.modac.model.comment.Comment;
import com.a608.modac.model.comment.CommentRequest;
import com.a608.modac.model.comment.CommentResponse;
import com.a608.modac.model.notification.Notification;
import com.a608.modac.model.user.User;
import com.a608.modac.repository.ArticleRepository;
import com.a608.modac.repository.CommentRepository;
import com.a608.modac.repository.NotificationRepository;
import com.a608.modac.repository.UserRepository;

@Service
public class CommentServiceImpl implements CommentService {
	final private CommentRepository commentRepository;
	final private ArticleRepository articleRepository;
	final private UserRepository userRepository;
	final private NotificationRepository notificationRepository;

	public CommentServiceImpl(CommentRepository commentRepository, ArticleRepository articleRepository,
		UserRepository userRepository, NotificationRepository notificationRepository) {
		this.commentRepository = commentRepository;
		this.articleRepository = articleRepository;
		this.userRepository = userRepository;
		this.notificationRepository = notificationRepository;
	}

	// 댓글 작성
	@Override
	public CommentResponse createComment(final CommentRequest commentRequest) {

		Article article = articleRepository.findById(commentRequest.getArticlesSeq())
			.orElseThrow(() -> new NoSuchElementException("NoArticle"));

		// 게시글의 댓글 수 업데이트
		article.updateCommentCount(1);
		articleRepository.save(article);

		// 댓글 저장
		User user = userRepository.findById(commentRequest.getUsersSeq())
			.orElseThrow(() -> new NoSuchElementException("NoUser"));
		Comment comment = commentRepository.save(commentRequest.toEntity(article, user));

		// 게시글 주인과 댓글 주인이 다르면 알림 보내기
		if (!article.getUser().equals(comment.getUser())) {
			notificationRepository.save(Notification.builder()
				.registeredTime(LocalDateTime.now())
				.article(article)
				.fromUser(comment.getUser())
				.toUser(article.getUser())
				.isRead((byte)1)
				.type("comment")
				.build());
		}

		return new CommentResponse(comment);
	}

	// 댓글 삭제
	@Override
	public void deleteComment(final Long seq) {

		Comment comment = commentRepository.findById(seq).orElseThrow(() -> new NoSuchElementException("NoComment"));
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
