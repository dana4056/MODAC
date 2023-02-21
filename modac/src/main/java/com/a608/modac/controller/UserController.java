package com.a608.modac.controller;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.a608.modac.model.follow.Follow;
import com.a608.modac.model.follow.FollowRequest;
import com.a608.modac.model.user.UserRequest;
import com.a608.modac.model.user.UserResponse;
import com.a608.modac.service.UserService;

import lombok.extern.slf4j.Slf4j;

@CrossOrigin(origins = {"*"})
@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {

	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping                        //회원 가입
	public ResponseEntity<?> signUp(@RequestBody final UserRequest userRequest) throws NoSuchAlgorithmException {
		System.out.println("user info: " + userRequest.toString());
		log.info("user info:" + userRequest.toString());
		UserResponse userResponse = userService.saveUser(userRequest);
		return new ResponseEntity<>(userResponse, HttpStatus.CREATED);
	}

	@GetMapping("/{seq}")            // 특정 회원 조회
	public ResponseEntity<?> findUser(@PathVariable("seq") Long seq) {
		UserResponse userResponse = userService.findUserBySeq(seq);
		return new ResponseEntity<UserResponse>(userResponse, HttpStatus.OK);
	}

	@PutMapping("/{seq}")            // 회원 수정
	public ResponseEntity<?> updateUser(@PathVariable("seq") Long seq, @RequestBody UserRequest userRequest) {
		UserResponse userResponse = userService.updateUser(seq, userRequest);
		return new ResponseEntity<>(userResponse, HttpStatus.CREATED);
	}

	@PutMapping("/{seq}/password")    // 비밀번호 수정
	public ResponseEntity<?> updatePassword(@PathVariable("seq") Long seq, @RequestBody String password) {
		userService.updatePassword(seq, password);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@PutMapping("/{seq}/point")        // 포인트 수정
	public ResponseEntity<?> updatePoint(@PathVariable("seq") Long seq, @RequestBody String point) {
		return new ResponseEntity<UserResponse>(userService.updatePoint(seq, point), HttpStatus.CREATED);
	}

	@DeleteMapping("/{seq}")            // 회원 탈퇴
	public ResponseEntity<?> deleteUser(@PathVariable("seq") Long seq) {
		userService.deleteUser(seq);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping("/find-id")            // 아이디 찾기(이메일로)
	public ResponseEntity<?> findIdByEmail(@RequestParam("email") String email) {
		String id = userService.findIdByEmail(email);
		return new ResponseEntity<String>(id, HttpStatus.OK);
	}

	@GetMapping("/find-password")    // 비밀번호 찾기(이메일로)
	public ResponseEntity<?> findPasswordByEmail(@RequestParam("email") String email) {
		String password = userService.findPasswordByEmail(email);
		return new ResponseEntity<String>(password, HttpStatus.OK);
	}

	@GetMapping("/check-nick")        // 닉네임 중복확인
	public ResponseEntity<?> checkAvailableNick(@RequestParam("nick") String nick) {
		userService.checkAvailableNick(nick);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping("/check-id")            // 아이디 중복확인
	public ResponseEntity<?> checkAvailableId(@RequestParam("id") String id) {
		userService.checkAvailableId(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping("/check-email")        // 이메일 중복확인
	public ResponseEntity<?> checkAvailableEmail(@RequestParam("email") String email) {
		userService.checkAvailableEmail(email);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PostMapping("/login")            // 로그인
	public ResponseEntity<?> login(@RequestBody UserRequest userRequest) throws NoSuchAlgorithmException {
		UserResponse loginUser = userService.login(userRequest);
		return new ResponseEntity<>(loginUser, HttpStatus.OK);
	}

	@PostMapping("/follow")            // 중복저장 안되게 조심해야하는디..
	public ResponseEntity<?> following(@RequestBody FollowRequest followRequest) {
		userService.following(followRequest);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@DeleteMapping("/follow/{seq}")        // 데이터가 중복저장되어있으면 에러
	public ResponseEntity<?> unfollowing(@PathVariable("seq") Long followSeq) {
		userService.unFollowing(followSeq);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping                        // 특정 회원의 팔로잉 혹은 팔로워 회원 목록 조회
	public ResponseEntity<?> findFollowingList(@RequestParam("filter") String filter, @RequestParam("user") Long seq) {
		List<Follow> list = null;
		if (filter.equals("following")) {
			list = userService.findFollowingList(seq);
		} else if (filter.equals("follower")) {
			list = userService.findFollowerList(seq);
		}
		return new ResponseEntity<List<Follow>>(list, HttpStatus.OK);
	}

	@GetMapping("/follow")
	public ResponseEntity<?> isFollowingThatUser(@RequestParam("from") Long fromSeq, @RequestParam("to") Long toSeq) {
		boolean isFollowing = userService.isFollowing(fromSeq, toSeq);
		return new ResponseEntity<>(isFollowing, HttpStatus.OK);
	}

}
