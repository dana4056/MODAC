package com.a608.modac.repository;

import com.a608.modac.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository  extends JpaRepository<User, Long> {
    User findUserBySeq(Long seq);

    User findUserByEmail(String email);

    User findUserByNickname(String nick);

    User findUserById(String id);
}
