package com.a608.modac.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor 	// 초기화 되지 않은 final 필드와 @NonNull 어노테이션이 붙은 필드에 대한 생성자 생성
@EnableWebSecurity			// 웹보안 활성화를위한 annotation (SpringSecurityFilterChain에 등록)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private final JwtTokenProvider jwtTokenProvider;

	// 암호화에 필요한 PasswordEncoder 를 Bean 등록합니다.
	@Bean
	public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}

	// authenticationManager를 Bean 등록합니다.
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	// @Bean
	// public CorsFilter corsFilter() {
	// 	UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	// 	CorsConfiguration config = new CorsConfiguration();
	// 	config.setAllowCredentials(true);
	// 	config.addAllowedOrigin("*");
	// 	config.addAllowedHeader("*");
	// 	config.addExposedHeader(HttpHeaders.AUTHORIZATION);
	// 	config.addAllowedMethod("*");
	// 	source.registerCorsConfiguration("/**", config);
	// 	return new CorsFilter(source);
	// }

	/*
	WebSecurityConfigurerAdapter: 스프링 시큐리티의 웹 보안 기능 초기화 및 설정
	HttpSecurity: 세부적인 보안기능을 설정할수 있는 API를 제공하는 클래스
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.httpBasic().disable() 														// rest api 만을 고려해 기본 설정 해제
			.csrf().disable() 															// csrf 보안 토큰 disable 처리
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // 토큰 기반 인증이므로 세션 사용 X
			.and()
			.cors().and()
			.authorizeRequests() 														// 요청에 대한 사용권한 체크
			// .antMatchers("/token/**").hasAnyRole("USER", "ADMIN")
			// .antMatchers("/delivery/post").permitAll()
			// .antMatchers("/users/1").hasRole("USER")
			.anyRequest().permitAll() // 그외 나머지 요청은 누구나 접근 가능
			.and()
			.addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class);

		http		// JwtAuthenticationFilter를 UsernamePasswordAuthenticationFilter 전에 넣기
			.exceptionHandling()
			.authenticationEntryPoint(new AuthenticationEntryPoint() {
				@Override
				public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws
					IOException, ServletException {
					response.setStatus(HttpStatus.FORBIDDEN.value());
					response.sendRedirect("/forbidden");
				}
			});
	}
}
