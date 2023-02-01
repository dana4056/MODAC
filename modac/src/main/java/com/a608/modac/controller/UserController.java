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

@CrossOrigin(origins = { "*" })
@RestController
@RequestMapping("/users")
public class UserController {

	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping						//회원 가입
	public ResponseEntity<?> signUp(@RequestBody final UserRequest userRequest){
		UserResponse userResponse = userService.saveUser(userRequest);
		return new ResponseEntity<>(userResponse,HttpStatus.CREATED);
	}

	@GetMapping("/{seq}")			// 특정 회원 조회
	public ResponseEntity<?> findUser(@PathVariable("seq") Long seq){
		UserResponse userResponse = userService.findUserBySeq(seq);
		System.out.println("controller");
		return new ResponseEntity<UserResponse>(userResponse, HttpStatus.OK);
	}

	@PutMapping("/{seq}")			// 회원 수정
	public ResponseEntity<?> updateUser(@PathVariable("seq") Long seq, @RequestBody UserRequest userRequest){

		System.out.println("++++++++++++회원정보 수정"+userRequest);
		UserResponse userResponse = userService.updateUser(seq, userRequest);
		return new ResponseEntity<>(userResponse, HttpStatus.CREATED);
	}

	@PutMapping("/{seq}/password")	// 비밀번호 수정
	public ResponseEntity<?> updatePassword(@PathVariable("seq") Long seq, @RequestBody String password){
		userService.updatePassword(seq, password);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@DeleteMapping("/{seq}")			// 회원 탈퇴
	public ResponseEntity<?> deleteUser(@PathVariable("seq") Long seq){
		userService.deleteUser(seq);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping("/find-id")			// 아이디 찾기(이메일로)
	public ResponseEntity<?> findIdByEmail(@RequestBody String email){
		String id = userService.findIdByEmail(email);
		return new ResponseEntity<String>(id, HttpStatus.OK);
	}

	@GetMapping("/find-password")	// 비밀번호 찾기(이메일로)
	public ResponseEntity<?> findPasswordByEmail(@RequestBody String email){
		String password = userService.findPasswordByEmail(email);
		return new ResponseEntity<String>(password, HttpStatus.OK);
	}

	@GetMapping("/check-nick")		// 닉네임 중복확인
	public ResponseEntity<?> checkAvailableNick(@RequestBody String nick){
		Boolean isAvailable = userService.checkAvailableNick(nick);
		return new ResponseEntity<Boolean>(isAvailable, HttpStatus.OK);
	}

	@GetMapping("/check-id")			// 아이디 중복확인
	public ResponseEntity<?> checkAvailableId(@RequestBody String id){
		Boolean isAvailable = userService.checkAvailableId(id);
		return new ResponseEntity<Boolean>(isAvailable, HttpStatus.OK);
	}

	@GetMapping("/check-email")		// 이메일 중복확인
	public ResponseEntity<?> checkAvailableEmail(@RequestBody String email){
		Boolean isAvailable = userService.checkAvailableEmail(email);
		return new ResponseEntity<Boolean>(isAvailable, HttpStatus.OK);
	}

	@PostMapping("/login")			// 로그인
	public ResponseEntity<?> login(@RequestBody UserRequest userRequest){
		Boolean isLoggedIn = userService.login(userRequest);
		return new ResponseEntity<Boolean>(isLoggedIn, HttpStatus.OK);
	}

	@PostMapping("/follow")			// 중복저장 안되게 조심해야하는디..
	public ResponseEntity<?> following(@RequestBody FollowRequest followRequest){
		userService.following(followRequest);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@DeleteMapping("/follow")		// 데이터가 중복저장되어있으면 에러
	public ResponseEntity<?> unfollowing(@RequestBody FollowRequest followRequest){
		userService.unFollowing(followRequest);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping						// 특정 회원의 팔로잉 혹은 팔로워 회원 목록 조회
	public ResponseEntity<?> findFollowingList(@RequestParam("filter") String filter, @RequestParam("user") Long seq){
		List<Follow> list = null;
		if(filter.equals("following")){
			list = userService.findFollowingList(seq);
		}else if(filter.equals("follower")){
			list = userService.findFollowerList(seq);
		}
		return new ResponseEntity<List<Follow>>(list, HttpStatus.OK);
	}
}
