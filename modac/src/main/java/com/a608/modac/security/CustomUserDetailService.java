package com.a608.modac.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.a608.modac.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CustomUserDetailService implements UserDetailsService {

	private final UserRepository userRepository;

	// @Override
	// public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
	// 	return (UserDetails)userRepository.findUserById(userId);
	// }


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println(username);
		System.out.println(userRepository.findUserById(username).toString());
		System.out.println(((UserDetails)userRepository.findUserById(username)).toString());
		return userRepository.findUserById(username);
	}
}
