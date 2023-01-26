package com.a608.modac.repository;

import com.a608.modac.model.follow.Follow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FollowRepository extends JpaRepository<Follow, Long> {

    Follow findFollowByFromUser_SeqAndToUser_Seq(Long fromSeq, Long toSeq);

    List<Follow> findAllByFromUser_Seq(Long seq);

    List<Follow> findAllByToUser_Seq(Long seq);
}
