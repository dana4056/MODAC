package com.a608.modac.model.comment;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

	// articlesSeq를 전달받아 일치하는 댓글을 리스트에 담아 반환
	List<Comment> findCommentsByArticlesSeq(final Long articlesSeq);

}
