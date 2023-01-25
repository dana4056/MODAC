package com.a608.modac.model.follow;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FollowRepository extends JpaRepository<Follow, Long> {

    Follow findFollowByFromSeqAndToSeq(Long fromSeq, Long toSeq);

    List<Follow> findAllByFromSeq(Long seq);

    List<Follow> findAllByToSeq(Long seq);
}
