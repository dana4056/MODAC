package com.a608.modac.repository;

import com.a608.modac.model.article.Like;
import com.a608.modac.model.article.LikeId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeRepository extends JpaRepository<Like, LikeId> {
	Long countByLikeId(LikeId likeId);
}
