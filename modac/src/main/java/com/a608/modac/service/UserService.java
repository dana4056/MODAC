package com.a608.modac.service;

import java.util.List;

import com.a608.modac.model.user.UserRequest;
import com.a608.modac.model.user.UserResponse;

public interface UserService {

	void saveUser(final UserRequest userRequest);	// 회원 저장

	void updateUser(final UserRequest userRequest); // 회원 수정 (닉네임, 이메일)

	void updatePassword(final String password);		// 회원 수정 (비밀번호 수정)

	void deleteUser(final Long seq);				// 회원 삭제

	UserResponse findUserBySeq(final Long seq);		// 회원 조회

	String findIdByEmail(final String email);		// 아이디 찾기 (이메일로)


}
