package com.a608.modac.security;

import java.util.Base64;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import com.a608.modac.model.user.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class JwtTokenProvider {

	private String secretKey = "modacSecretKey";

	// 토큰 유효시간 30분
	private long tokenValidTime = 30 * 60 * 10000000L;

	private final UserDetailsService userDetailService;
	// private final CustomUserDetailService userDetailService;
	@PostConstruct
	protected void init() {		// 객체 초기화 및 secretKey를 Base64로 인코딩
		secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
	}

	public String createToken(User user) {

		Claims claims = Jwts.claims().setSubject(user.getId()); // JWT payload 에 저장되는 정보단위
		claims.put("userSeq", user.getSeq());					// 정보는 key - value 쌍으로 저장
		claims.put("nickname", user.getNickname());
		claims.put("roles",  Collections.singletonList("ROLE_USER"));
		Date now = new Date();
		return Jwts.builder()
			.setClaims(claims) 											// 정보 저장
			.setIssuedAt(now) 											// 토큰 발행 시간 정보
			.setExpiration(new Date(now.getTime() + tokenValidTime))	// set Expire Time
			.signWith(SignatureAlgorithm.HS256, secretKey)  			// 사용할 암호화 알고리즘과 signature 에 들어갈 secret값
			.compact();
	}


	// JWT 토큰에서 인증 정보 조회
	public Authentication getAuthentication(String token) {
		UserDetails userDetails = userDetailService.loadUserByUsername(this.getUserPk(token));
		if(userDetails == null){
			return new UsernamePasswordAuthenticationToken(null, "", null);
		}
		return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
	}

	// 토큰에서 회원 정보 추출
	public String getUserPk(String token) {
		String subject = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
		return subject;
	}

	// Request의 Header에서 token 값을 가져옵니다. "X-AUTH-TOKEN" : "TOKEN값'
	public String resolveToken(HttpServletRequest request) {
		return request.getHeader("X-AUTH-TOKEN");
	}

	// 토큰의 유효성 + 만료일자 확인
	public boolean validateToken(String jwtToken) {
		try {
			Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwtToken);
			return !claims.getBody().getExpiration().before(new Date());
		} catch (Exception e) {
			return false;
		}
	}
}