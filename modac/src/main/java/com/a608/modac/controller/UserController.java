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
import com.a608.modac.model.follow.FollowResponse;
import com.a608.modac.model.user.UserRequest;
import com.a608.modac.model.user.UserResponse;
import com.a608.modac.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;

@Api(tags = "User Controller", description = "사용자 관련 API")
@CrossOrigin(origins = {"*"})
@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {

	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@Operation(summary = "회원 가입")
	@PostMapping                        //회원 가입
	public ResponseEntity<?> signUp(@RequestBody final UserRequest userRequest) throws NoSuchAlgorithmException {
		System.out.println("user info: " + userRequest.toString());
		log.info("user info:" + userRequest.toString());
		UserResponse userResponse = userService.saveUser(userRequest);
		return new ResponseEntity<>(userResponse, HttpStatus.CREATED);
	}

	@Operation(summary = "회원 정보 조회", description = "특정 회원 (seq) 정보 조회")
	@GetMapping("/{seq}")            // 특정 회원 조회
	public ResponseEntity<?> findUser(@PathVariable("seq") Long seq) {
		UserResponse userResponse = userService.findUserBySeq(seq);
		return new ResponseEntity<UserResponse>(userResponse, HttpStatus.OK);
	}

	@Operation(summary = "이메일로 특정 회원 조회", description = "비밀번호 변경 시 (로그인 화면에서) 이메일 인증 -> 회원 정보 조회해서 해당 회원 비번 변경")
	@GetMapping("/find-by-email")            // 이메일로 특정 회원 조회
	public ResponseEntity<?> findUserByEmail(@RequestParam("email") String email) {
		UserResponse userResponse = userService.findUserByEmail(email);
		return new ResponseEntity<UserResponse>(userResponse, HttpStatus.OK);
	}

	@Operation(summary = "회원 정보 수정", description = "특정 회원(seq) 정보 수정<br>"
		+ "-> 닉네임, 이메일만 수정 가능 (하나만 수정 시에도 둘 다 보내야 함)")
	@PutMapping("/{seq}")            // 회원 수정
	public ResponseEntity<?> updateUser(@PathVariable("seq") Long seq, @RequestBody UserRequest userRequest) {
		UserResponse userResponse = userService.updateUser(seq, userRequest);
		return new ResponseEntity<>(userResponse, HttpStatus.CREATED);
	}

	@Operation(summary = "비밀번호 변경", description = "특정 회원(seq)의 비밀 번호 변경")
	@PutMapping("/{seq}/password")    // 비밀번호 수정
	public ResponseEntity<?> updatePassword(@PathVariable("seq") Long seq, @RequestBody String password) throws
		NoSuchAlgorithmException {
		userService.updatePassword(seq, password);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@Operation(summary = "포인트 수정", description = "특정 회원(seq)의 포인트 수정 -> 멤버십 등급 갱신 자동")
	@PutMapping("/{seq}/point")        // 포인트 수정
	public ResponseEntity<?> updatePoint(@PathVariable("seq") Long seq, @RequestBody String point) {
		return new ResponseEntity<UserResponse>(userService.updatePoint(seq, point), HttpStatus.CREATED);
	}

	@Operation(summary = "회원 탈퇴 (삭제)", description = "특정 회원(seq) 삭제")
	@DeleteMapping("/{seq}")            // 회원 탈퇴
	public ResponseEntity<?> deleteUser(@PathVariable("seq") Long seq) {
		userService.deleteUser(seq);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@Operation(summary = "아이디 찾기", description = "이메일 통해 아이디 찾기")
	@GetMapping("/find-id")            // 아이디 찾기(이메일로)
	public ResponseEntity<?> findIdByEmail(@RequestParam("email") String email) {
		String id = userService.findIdByEmail(email);
		return new ResponseEntity<String>(id, HttpStatus.OK);
	}

	// @Operation(summary = "비밀 번호 찾기", description = "이메일로 비밀번호 찾기 (근데 비밀번호 암호화 해놔서 더이상 사용하지 않는 API 인듯)")
	// @GetMapping("/find-password")    // 비밀번호 찾기(이메일로)
	// public ResponseEntity<?> findPasswordByEmail(@RequestParam("email") String email) {
	// 	String password = userService.findPasswordByEmail(email);
	// 	return new ResponseEntity<String>(password, HttpStatus.OK);
	// }

	@Operation(summary = "닉네임 중복확인", description = "해당 닉네임(nick)의 중복 여부 확인")
	@GetMapping("/check-nick")        // 닉네임 중복확인
	public ResponseEntity<?> checkAvailableNick(@RequestParam("nick") String nick) {
		userService.checkAvailableNick(nick);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@Operation(summary = "아이디 중복확인", description = "해당 아이디(id)의 중복 여부 확인")
	@GetMapping("/check-id")            // 아이디 중복확인
	public ResponseEntity<?> checkAvailableId(@RequestParam("id") String id) {
		userService.checkAvailableId(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@Operation(summary = "이메일 중복확인", description = "해당 이메일(email)의 중복 여부 확인")
	@GetMapping("/check-email")        // 이메일 중복확인
	public ResponseEntity<?> checkAvailableEmail(@RequestParam("email") String email) {
		userService.checkAvailableEmail(email);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@Operation(summary = "로그인", description = "userRequest 객체에 id와 비밀번호만 채워서 요청<br>"
		+ "-> 로그인 성공 시 로그인 유저 정보가 담긴 토큰 반환")
	@PostMapping("/login")            // 로그인
	public ResponseEntity<?> login(@RequestBody UserRequest userRequest) throws NoSuchAlgorithmException {
		UserResponse loginUser = userService.login(userRequest);
		return new ResponseEntity<>(loginUser, HttpStatus.OK);
	}

	@Operation(summary = "팔로잉", description = "fromSeq 사용자가 toSeq 사용자를 팔로잉")
	@PostMapping("/follow")
	public ResponseEntity<?> following(@RequestBody FollowRequest followRequest) {
		userService.following(followRequest);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@Operation(summary = "언팔로우", description = "고유번호 통해 팔로잉 관계 삭제")
	@DeleteMapping("/follow/{seq}")
	public ResponseEntity<?> unfollowing(@PathVariable("seq") Long followSeq) {
		userService.unFollowing(followSeq);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@Operation(summary = "팔로잉/팔로우 회원 목록 조회 (친구 조회)", description = "filter에\"following\"이라고 문자열 그대로 입력 -> 팔로잉하고 있는 친구 목록<br>"
		+ "filter에\"follower\"라고 문자열 그대로 입력 -> 난 팔로우하는 친구 목록<br>"
		+ "-> 필터를 기준으로 내가 팔로잉 하는 친구, 나를 팔로잉하는 친구 목록 조회")
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

	@Operation(summary = "팔로우 여부 조회", description = "from 사용자가 to 사용자를 팔로우 하고 있는지 여부 조회")
	@GetMapping("/follow")
	public ResponseEntity<?> isFollowingThatUser(@RequestParam("from") Long fromSeq, @RequestParam("to") Long toSeq) {
		FollowResponse followResponse = userService.isFollowing(fromSeq, toSeq);
		return new ResponseEntity<>(followResponse, HttpStatus.OK);
	}

}
