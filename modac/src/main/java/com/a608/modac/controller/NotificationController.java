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

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;

@Api(tags = "Notification Controller", description = "알림 관련 API")
@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/notifications")
public class NotificationController {

	private final NotificationService notificationService;

	public NotificationController(NotificationService notificationService) {
		this.notificationService = notificationService;
	}

	// 알림 생성
	@Operation(summary = "알림 생성", description = "from 사용자가 to 사용자에게 type 종류의 알람을 생성함<br>"
		+ "<br><b>[type]</b><br>"
		+ "<b>\"like\"</b>: 새 게시글 좋아요<br>"
		+ "<b>\"comment\"</b>: 새 게시글 댓글<br>"
		+ "<b>\"follow\"</b>: 새 팔로워<br>")
	@PostMapping
	public ResponseEntity<?> createNotification(@RequestBody final NotificationRequest notificationRequest) {
		return new ResponseEntity<NotificationResponse>(notificationService.createNotification(notificationRequest),
			HttpStatus.CREATED);
	}

	// 새 알림 개수 조회
	@Operation(summary = "읽지않은 알림 개수 조회", description = "사용자(usersSeq)가 아직 읽지 않은 알림 개수 조회")
	@GetMapping
	public ResponseEntity<?> countUnreadNotification(@RequestParam("user") final Long usersSeq) {
		return new ResponseEntity<Integer>(notificationService.countUnreadNotification(usersSeq), HttpStatus.OK);
	}

	// 새 알림 읽음 처리
	@Operation(summary = "알림 목록 읽음 처리(일괄)", description = "사용자(usersSeq)가 읽지 않은 알림 목록 일괄적으로 읽음 처리" 
		+ "<br> -> 알림 목록 확인")
	@PutMapping
	public ResponseEntity<?> updateAllIsRead(@RequestBody String usersSeq) {
		notificationService.updateAllIsRead(Long.parseLong(usersSeq));
		return new ResponseEntity<String>(HttpStatus.OK);
	}

	// 알림 목록 조회
	@Operation(summary = "알림 목록 조회", description = "사용자(usersSeq)에게 온 모든 알람 목록 조회<br>" 
		+ "-> 읽지 않은 요소를 가장 상단에 오도록 정렬된 상태로")
	@GetMapping("/list")
	public ResponseEntity<?> findNotifications(@RequestParam("user") final Long usersSeq) {
		return new ResponseEntity<List<NotificationResponse>>(notificationService.findNotifications(usersSeq),
			HttpStatus.OK);
	}

}