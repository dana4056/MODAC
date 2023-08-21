package com.a608.modac.service;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import com.a608.modac.model.follow.Follow;
import com.a608.modac.model.follow.FollowRequest;
import com.a608.modac.model.follow.FollowResponse;
import com.a608.modac.model.user.UserRequest;
import com.a608.modac.model.user.UserResponse;

public interface UserService {

	UserResponse saveUser(final UserRequest userRequest) throws NoSuchAlgorithmException;	// 회원 가입

	UserResponse updateUser(final Long seq, final UserRequest userRequest); // 회원 수정 (닉네임, 이메일)

	void updatePassword(final Long seq, final String password) throws NoSuchAlgorithmException;		// 회원 수정 (비밀번호 수정)

	void deleteUser(final Long seq);				// 회원 삭제

	UserResponse findUserBySeq(final Long seq);		// 회원 조회

	UserResponse findUserByEmail(final String email);	// 이메일로 회원 조회

	void checkAvailableNick(final String nick);	// 닉네임 중복확인

	void checkAvailableId(final String id);		// 아이디 중복확인

	void checkAvailableEmail(final String email);// 이메일 중복확인

	UserResponse login(UserRequest userRequest) throws NoSuchAlgorithmException;			// 로그인
	
	void following(FollowRequest followRequest);	// 팔로잉

	void unFollowing(Long followSeq);    // 언팔로잉

	List<Follow> findFollowingList(Long seq);

	List<Follow> findFollowerList(Long seq);

	UserResponse updatePoint(Long seq, String point); // 포인트 추가

	FollowResponse isFollowing(Long fromSeq, Long toSeq);
}
