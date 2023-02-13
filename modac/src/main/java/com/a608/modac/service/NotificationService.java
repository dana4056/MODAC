package com.a608.modac.service;

import java.util.List;

import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import com.a608.modac.model.notification.NotificationRequest;
import com.a608.modac.model.notification.NotificationResponse;

public interface NotificationService {
	// 알림 생성
	NotificationResponse createNotification(final NotificationRequest notificationRequest);

	// 새 알림 개수 조회
	Integer countUnreadNotification(final Long usersSeq);

	// 새 알림 읽음 처리
	void updateAllIsRead(final Long usersSeq);

	// 알림 목록 조회
	List<NotificationResponse> findNotifications(final Long usersSeq);

	// 알림 구독
	SseEmitter subscribe(Long usersSeq, String lastEventId);
}