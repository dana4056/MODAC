package com.a608.modac.repository;

import com.a608.modac.model.article.Article;
import com.a608.modac.model.article.Like;
import com.a608.modac.model.user.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {
	Long countBySeq(Long seq);
	Like findLikeByArticle_SeqAndUser_Seq(Long articlesSeq, Long usersSeq);
	Like findByArticleAndUser(Article article, User user);
}
