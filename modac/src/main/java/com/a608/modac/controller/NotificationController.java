package com.a608.modac.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.a608.modac.model.notification.NotificationRequest;
import com.a608.modac.model.notification.NotificationResponse;
import com.a608.modac.service.NotificationService;

@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/notifications")
public class NotificationController {

	private final NotificationService notificationService;

	public NotificationController(NotificationService notificationService) {
		this.notificationService = notificationService;
	}

	// 알림 생성
	@PostMapping
	public ResponseEntity<?> createNotification(@RequestBody final NotificationRequest notificationRequest) {
		return new ResponseEntity<NotificationResponse>(notificationService.createNotification(notificationRequest),
			HttpStatus.CREATED);
	}

	// 새 알림 개수 조회
	@GetMapping
	public ResponseEntity<?> countUnreadNotification(@RequestParam("user") final Long usersSeq) {
		return new ResponseEntity<Integer>(notificationService.countUnreadNotification(usersSeq), HttpStatus.OK);
	}

	// 새 알림 읽음 처리
	@PutMapping
	public ResponseEntity<?> updateAllIsRead(@RequestBody String usersSeq) {
		notificationService.updateAllIsRead(Long.parseLong(usersSeq));
		return new ResponseEntity<String>(HttpStatus.OK);
	}

	// 알림 목록 조회
	@GetMapping("/list")
	public ResponseEntity<?> findNotifications(@RequestParam("user") final Long usersSeq) {
		return new ResponseEntity<List<NotificationResponse>>(notificationService.findNotifications(usersSeq),
			HttpStatus.OK);
	}

}