package com.a608.modac.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.a608.modac.model.follow.Follow;

@Repository
public interface FollowRepository extends JpaRepository<Follow, Long> {
    Follow findFollowByFromUser_SeqAndToUser_Seq(Long fromSeq, Long toSeq);

    List<Follow> findFollowsByFromUser_SeqAndToUser_Seq(final Long fromUsersSeq, final Long toUsersSeq);

    List<Follow> findAllByFromUser_Seq(Long seq);

    List<Follow> findAllByToUser_Seq(Long seq);
}
