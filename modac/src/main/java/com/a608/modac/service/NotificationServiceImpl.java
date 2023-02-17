package com.a608.modac.service;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.a608.modac.model.article.Article;
import com.a608.modac.model.notification.Notification;
import com.a608.modac.model.notification.NotificationRequest;
import com.a608.modac.model.notification.NotificationResponse;
import com.a608.modac.model.user.User;
import com.a608.modac.repository.ArticleRepository;
import com.a608.modac.repository.NotificationRepository;
import com.a608.modac.repository.UserRepository;

@Service
public class NotificationServiceImpl implements NotificationService {
	final private NotificationRepository notificationRepository;
	final private ArticleRepository articleRepository;
	final private UserRepository userRepository;

	public NotificationServiceImpl(NotificationRepository notificationRepository,
		ArticleRepository articleRepository, UserRepository userRepository) {
		this.notificationRepository = notificationRepository;
		this.articleRepository = articleRepository;
		this.userRepository = userRepository;
	}

	// 알림 생성
	@Override
	public NotificationResponse createNotification(NotificationRequest notificationRequest) {
		// Validation
		User fromUser = userRepository.findById(notificationRequest.getFromUsersSeq())
			.orElseThrow(() -> new NoSuchElementException("NoFromUser"));
		User toUser = userRepository.findById(notificationRequest.getToUsersSeq())
			.orElseThrow(() -> new NoSuchElementException("NoToUser"));
		Article article = articleRepository.findById(notificationRequest.getArticlesSeq()).orElse(null);
		String type = notificationRequest.getType();
		// Save
		Notification notification = notificationRequest.toEntity(article, fromUser, toUser, type);
		notificationRepository.save(notification);
		return new NotificationResponse(notification);
	}

	// 새 알림 개수 조회
	@Override
	public Integer countUnreadNotification(Long usersSeq) {
		// Validation
		User toUser = userRepository.findById(usersSeq).orElseThrow(() -> new NoSuchElementException("NoUser"));
		Byte isRead = 1;
		// Count
		return notificationRepository.countByToUserAndIsRead(toUser, isRead);
	}

	// 새 알림 읽음 처리
	@Override
	public void updateAllIsRead(Long usersSeq) {
		// Validation
		User toUser = userRepository.findById(usersSeq).orElseThrow(() -> new NoSuchElementException("NoUser"));
		Byte isRead = 1;
		// Find
		List<Notification> notifications = notificationRepository.findAllByToUserAndIsRead(toUser, isRead);
		for (Notification notification : notifications) {
			notification.updateIsRead();
			notificationRepository.save(notification);
		}
	}

	// 알림 목록 조회
	@Override
	public List<NotificationResponse> findNotifications(Long usersSeq) {
		User toUser = userRepository.findById(usersSeq).orElseThrow(() -> new NoSuchElementException("NoUser"));
		List<Notification> notifications = notificationRepository.findAllByToUser(toUser);
		Collections.sort(notifications, (o1, o2) -> o1.getIsRead() != o2.getIsRead() ? o2.getIsRead() - o1.getIsRead() :
			o1.getRegisteredTime().isBefore(o2.getRegisteredTime()) ? 1 : -1);
		return notifications.stream().map(NotificationResponse::new).collect(Collectors.toList());
	}

}