package com.a608.modac.service;

import java.util.List;

import com.a608.modac.model.follow.Follow;
import com.a608.modac.model.follow.FollowRequest;
import com.a608.modac.model.user.UserRequest;
import com.a608.modac.model.user.UserResponse;

public interface UserService {

	UserResponse saveUser(final UserRequest userRequest);	// 회원 가입

	UserResponse updateUser(final Long seq, final UserRequest userRequest); // 회원 수정 (닉네임, 이메일)

	void updatePassword(final Long seq, final String password);		// 회원 수정 (비밀번호 수정)

	void deleteUser(final Long seq);				// 회원 삭제

	UserResponse findUserBySeq(final Long seq);		// 회원 조회

	String findIdByEmail(final String email);		// 아이디 찾기 (이메일로)

	String findPasswordByEmail(final String email);	// 비밀번호 찾기 (이메일로)

	boolean checkAvailableNick(final String nick);	// 닉네임 중복확인

	boolean checkAvailableId(final String id);		// 아이디 중복확인
	
	boolean checkAvailableEmail(final String email);// 이메일 중복확인

	String login(UserRequest userRequest);			// 로그인
	
	void following(FollowRequest followRequest);	// 팔로잉

	void unFollowing(FollowRequest followRequest);	// 언팔로잉

	List<Follow> findFollowingList(Long seq);

	List<Follow> findFollowerList(Long seq);
}
