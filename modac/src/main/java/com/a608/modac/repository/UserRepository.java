package com.a608.modac.repository;

import java.util.List;
import java.util.Optional;

import com.a608.modac.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository  extends JpaRepository<User, Long> {

    Optional<User> findUserByEmail(String email);

    Optional<User> findUserByNickname(String nick);

    Optional<User> findUserById(String id);

    List<User> findAllByNicknameContaining(String keyword);
}
