package com.a608.modac.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.a608.modac.model.notification.Notification;
import com.a608.modac.model.user.User;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
	Integer countByToUserAndIsRead(User user, Byte IsRead);

	List<Notification> findAllByToUserAndIsRead(User user, Byte IsRead);

	List<Notification> findAllByToUser(User user);
}