package com.a608.modac.repository;

import java.util.List;

import com.a608.modac.model.article.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {

	// usersId를 전달받아 일치하는 게시글을 리스트에 담아 반환
	List<Article> findByUsersSeq(Long usersSeq);


}
