package com.a608.modac.service.user;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

import java.security.NoSuchAlgorithmException;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.redis.core.RedisTemplate;

import com.a608.modac.model.chat.DirectMessage;
import com.a608.modac.model.user.User;
import com.a608.modac.model.user.UserRequest;
import com.a608.modac.model.user.UserResponse;
import com.a608.modac.repository.ChatDirectRepository;
import com.a608.modac.repository.ChatRoomRepository;
import com.a608.modac.repository.FollowRepository;
import com.a608.modac.repository.NotificationRepository;
import com.a608.modac.repository.UserRepository;
import com.a608.modac.security.JwtTokenProvider;
import com.a608.modac.service.UserServiceImpl;



@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

	@Mock
	UserRepository userRepository;
	@Mock
	FollowRepository followRepository;
	@Mock
	ChatRoomRepository chatRoomRepository;
	@Mock
	NotificationRepository notificationRepository;
	@Mock
	JwtTokenProvider jwtTokenProvider;
	@Mock
	RedisTemplate<String, DirectMessage> redisTemplate;
	@Mock
	ChatDirectRepository chatDirectRepository;

	@InjectMocks
	UserServiceImpl userService;

	@Mock
	UserResponse userResponse;

	@Test
	// @Disabled	// 회원가입 로직에 동적으로 랜덤값 만드는 부분(스킨 번호, 비밀번호 암호화) 때문에 테스트 쉽지 않음
	@DisplayName("회원 가입 테스트 (성공)")
	void saveUser_success() throws NoSuchAlgorithmException {
		// given
		String userId = "Modac";
		String email = "modac@naver.com";
		String nick = "modac";
		String password = "Test1234!!";
		UserRequest userRequest = new UserRequest(null, userId, nick, email, password);

		given(userRepository.save(any())).willReturn(User.builder()
			.seq(1L)
			.email("modac@naver.com")
			.id("Modac")
			.nickname("modac")
			.singleTheme(null)
			.totalSecond(0L).build());

		// when
		userRequest.setPassword(password);
		UserResponse userResponse = userService.saveUser(userRequest);

		// then
		assertEquals(userRequest.getId(), userResponse.getId());
	}

	@Test
	@DisplayName("회원 수정 테스트 (성공)")
	void updateUser_success(){
		// given
		Long seq = 1L;
		String userId = "Modac";
		String email = "modac_after@naver.com";
		String nick = "modac_after";
		String password = "Test1234!!";
		UserRequest userRequest = new UserRequest(seq, userId, nick, email, password);

		given(userRepository.findById(seq)).willReturn(Optional.ofNullable(User.builder()
			.seq(seq)
			.id(userId)
			.nickname("modac")
			.email("modac@naver.com")
			.singleTheme(null)
			.totalSecond(0L).build()));

		given(userRepository.save(any()))
			.willReturn(User.builder()
			.seq(seq)
			.id(userId)
			.nickname(nick)
			.email(email)
			.singleTheme(null)
			.totalSecond(0L).build());

		// when
		UserResponse userResponse = userService.updateUser(seq, userRequest);

		// then
		assertEquals(userRequest.getNickname(), userResponse.getNickname());
		assertEquals(userRequest.getEmail(), userResponse.getEmail());
	}

	@Test
	@DisplayName("회원 수정 테스트 (실패 - 요청한 사용자 없음)")
	void updateUser_fail_noUser(){
		// given
		Long seq = 1L;
		String userId = "noUser";
		String email = "noUser@naver.com";
		String nick = "noUser";
		String password = "noUser";
		UserRequest userRequest = new UserRequest(seq, userId, nick, email, password);

		given(userRepository.findById(seq)).willThrow(new NoSuchElementException("NoUser"));

		// when
		NoSuchElementException e = assertThrows(NoSuchElementException.class,
			() -> userService.updateUser(seq, userRequest));

		// then
		assertThat(e.getMessage()).isEqualTo("NoUser");
	}


	@Test
	@DisplayName("닉네임 중복 테스트 (중복이 아닌 경우)")
	void checkAvailableNick_unique(){
		// given
		String name = "uniqueNick";
		given(userRepository.findUserByNickname("uniqueNick")).willThrow(new NoSuchElementException("NoUser"));

		//when
		NoSuchElementException e = assertThrows(NoSuchElementException.class, () -> userService.checkAvailableNick(name));

		//then
		assertThat(e.getMessage()).isEqualTo("NoUser");
	}

	@Test
	@DisplayName("닉네임 중복 테스트 (중복인 경우)")
	void checkAvailableNick_duplication(){
		// given
		String name = "modac";
		given(userRepository.findUserByNickname("modac")).willReturn(Optional.ofNullable(User.builder()
			.seq(1L)
			.email("modac@naver.com")
			.id("Modac")
			.nickname("modac")
			.password("--암호화된 비밀번호--")
			.catSkin((byte)4)
			.singleTheme(null)
			.totalSecond(0L)
			.salt("6l3PSsxYyszRnGlAtranIQ==").build()));

		// when
		userService.checkAvailableNick(name);

		// then: No exception should be thrown
	}

	@Test
	@DisplayName("로그인 테스트 (성공)")
	void login_success() throws NoSuchAlgorithmException {
		// given
		String userId = "Modac";
		String password = "Test1234!!";
		UserRequest user = new UserRequest(null, userId, null, null, password);
		given(userRepository.findUserById(userId)).willReturn(Optional.ofNullable(User.builder()
			.seq(1L)
			.email("modac@naver.com")
			.id("Modac")
			.nickname("modac")
			.password("4005b00cce62a9948ab29d6b3829d9a69cabdc1eeef571f31e8918d58d87fdf737a8d6a2eb6d3b60f792ff939983cf04e23301ffda57e387711d7b99d51f0013")
			.catSkin((byte)4)
			.singleTheme(null)
			.totalSecond(0L)
			.salt("6l3PSsxYyszRnGlAtranIQ==").build()));

		// when
		UserResponse result = userService.login(user);

		// then
		assertEquals(result.getNickname(), "modac");
	}

	@Test
	@DisplayName("로그인 테스트 (실패)")
	void login_fail() throws NoSuchAlgorithmException {
		// given
		String userId = "Modac";
		String password = "wrongPassword";
		UserRequest user = new UserRequest(null, userId, null, null, password);
		given(userRepository.findUserById(userId)).willReturn(Optional.ofNullable(User.builder()
			.seq(1L)
			.email("modac@naver.com")
			.id("Modac")
			.nickname("modac")
			.password("4005b00cce62a9948ab29d6b3829d9a69cabdc1eeef571f31e8918d58d87fdf737a8d6a2eb6d3b60f792ff939983cf04e23301ffda57e387711d7b99d51f0013")
			.catSkin((byte)4)
			.singleTheme(null)
			.totalSecond(0L)
			.salt("6l3PSsxYyszRnGlAtranIQ==").build()));

		// when
		UserResponse result = userService.login(user);

		// then
		assertNull(result);
	}

	@Test
	@DisplayName("이메일로 회원핮기 테스트 (성공)")
	void findUserByEmail_success(){
		// given
		String email = "modac@naver.com";
		given(userRepository.findUserByEmail(email)).willReturn(Optional.ofNullable(User.builder().
			seq(1L)
			.email(email)
			.nickname("modac")
			.id("Modac").build()));

		// when
		UserResponse userResponse = userService.findUserByEmail(email);

		// then
		assertEquals(userResponse.getSeq(), 1L);
		assertEquals(userResponse.getEmail(), email);
	}

	@Test
	@DisplayName("이메일로 회원핮기 테스트 (실패 - 요청한 사용자 없음)")
	void findUserByEmail_fail(){
		// given
		String email = "noUser@naver.com";
		given(userRepository.findUserByEmail(email)).willThrow(new NoSuchElementException("NoUser"));

		// when
		NoSuchElementException e = assertThrows(NoSuchElementException.class,
			() -> userService.findUserByEmail(email));

		// then
		assertEquals(e.getMessage(), "NoUser");
	}

	@Test
	@DisplayName("특정 유저의 포인트 갱신 (성공)")
	void updatePoint(){
		// given\
		Long seq = 1L;
		String point = "15";
		User user = User.builder().seq(seq).build();

		given(userRepository.findById(seq)).willReturn(Optional.ofNullable(User.builder()
			.seq(seq).build()));

		User user2 = User.builder().seq(seq).build();
		user2.updatePoint(Integer.parseInt(point));
		given(userRepository.save(any())).willReturn(user2);

		// when
		UserResponse userResponse = userService.updatePoint(seq, point);

		// then
		assertEquals(userResponse.getPoint(), Integer.parseInt(point));
	}
}
