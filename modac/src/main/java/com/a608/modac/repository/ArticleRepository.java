package com.a608.modac.repository;

import java.time.LocalDateTime;
import java.util.List;

import com.a608.modac.model.article.Article;
import com.a608.modac.model.user.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {

	// user가 일치하는 게시글 리스트 반환
	List<Article> findByUser(User user);

	// user와 publicType이 일치하는 게시글 리스트 반환
	List<Article> findAllByUserAndPublicType(User user, Byte publicType);

	// 최근 일주일간 사용자가 작성한 게시글 리스트 반환
	List<Article> findAllByUserAndRegisteredTimeGreaterThanEqual(User user, LocalDateTime localdateTime);

}
