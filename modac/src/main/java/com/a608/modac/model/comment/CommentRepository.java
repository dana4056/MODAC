package com.a608.modac.model.comment;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository {

	// articlesSeq를 전달받아 일치하는 댓글을 리스트에 담아 반환
	List<Comment> findByArticlesSeq(Long articlesSeq);

}
