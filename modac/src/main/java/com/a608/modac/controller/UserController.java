package com.a608.modac.controller;

import com.a608.modac.model.follow.Follow;
import com.a608.modac.model.follow.FollowRequest;
import com.a608.modac.model.user.UserRequest;
import com.a608.modac.model.user.UserResponse;
import com.a608.modac.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping				//회원 가입
	public ResponseEntity<?> signUp(@RequestBody final UserRequest userRequest){
		userService.saveUser(userRequest);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping("/{seq}")	// 특정 회원 조회
	public ResponseEntity<?> findUser(@PathVariable("seq") Long seq){
		UserResponse userResponse = userService.findUserBySeq(seq);
		return new ResponseEntity<UserResponse>(userResponse, HttpStatus.OK);
	}

	@PutMapping("/{seq}")	// 회원 수정
	public ResponseEntity<?> updateUser(@PathVariable("seq") Long seq, @RequestBody UserRequest userRequest){
		userService.updateUser(seq, userRequest);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PutMapping("/{seq}/password")	// 비밀번호 수정
	public ResponseEntity<?> updatePassword(@PathVariable("seq") Long seq, @RequestBody String password){
		userService.updatePassword(seq, password);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@DeleteMapping("/{seq}")
	public ResponseEntity<?> deleteUser(@PathVariable("seq") Long seq){
		userService.deleteUser(seq);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping("/find-id")
	public ResponseEntity<?> findIdByEmail(@RequestBody String email){
		String id = userService.findIdByEmail(email);
		return new ResponseEntity<String>(id, HttpStatus.OK);
	}

	@GetMapping("/find-password")
	public ResponseEntity<?> findPasswordByEmail(@RequestBody String email){
		String password = userService.findPasswordByEmail(email);
		return new ResponseEntity<String>(password, HttpStatus.OK);
	}

	@GetMapping("/check-nick")
	public ResponseEntity<?> checkAvailableNick(@RequestBody String nick){
		Boolean isAvailable = userService.checkAvailableNick(nick);
		return new ResponseEntity<Boolean>(isAvailable, HttpStatus.OK);
	}

	@GetMapping("/check-id")
	public ResponseEntity<?> checkAvailableId(@RequestBody String id){
		Boolean isAvailable = userService.checkAvailableId(id);
		return new ResponseEntity<Boolean>(isAvailable, HttpStatus.OK);
	}

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody UserRequest userRequest){
		Boolean isLoggedIn = userService.login(userRequest);
		return new ResponseEntity<Boolean>(isLoggedIn, HttpStatus.OK);
	}

	@PostMapping("/follow")		// 중복저장 안되게 조심해야하는디..
	public ResponseEntity<?> following(@RequestBody FollowRequest followRequest){
		userService.following(followRequest);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@DeleteMapping("/follow")	// 데이터가 중복저장되어있으면 에러
	public ResponseEntity<?> unfollowing(@RequestBody FollowRequest followRequest){
		userService.unFollowing(followRequest);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping	//Follow 객체가 아니라 UserResponse 리스트가 나오도록 수정쓰
	public ResponseEntity<?> findFollowingList(@RequestParam("following") Long seq){
		List<Follow> list = userService.findFollowingList(seq);
		return new ResponseEntity<List<Follow>>(list, HttpStatus.OK);
	}

	@GetMapping	//Follow 객체가 아니라 UserResponse 리스트가 나오도록 수정쓰
	public ResponseEntity<?> findFollowerList(@RequestParam("follower") Long seq){
		List<Follow> list = userService.findFollowerList(seq);
		return new ResponseEntity<List<Follow>>(list, HttpStatus.OK);
	}

}
