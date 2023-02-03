package com.a608.modac.model.user;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@Entity
@ToString
@DynamicInsert	//not null인 컬럼에 데이터 저장 안해줄때 자동으로 default값으로 매핑해줌
@NoArgsConstructor
@Table(name = "users")
public class User implements UserDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long seq;

	@Column(name = "id")
	private String id;

	@Column(name="nickname")
	private String nickname;

	@Column(name="email")
	private String email;

	@Column(name = "password")
	private String password;

	@Column(name = "cat_skin")
	private Byte catSkin;        // UI로 표시될 고양이 스킨 번호 (일단 12개..)

	@Column(name = "single_theme")
	private String singleTheme;    //싱글룸 테마(우주....)

	@Column(name = "total_second")
	private Long totalSecond; //인생 총 공부시간

	@Column(name = "membership_level")
	private String membershipLevel;

	@Column(name = "point")
	private Integer point;

	@Column(name = "max_point")
	private Integer maxPoint;

	@Builder
	public User(Long seq, String id, String nickname, String email, String password, Byte catSkin, String singleTheme,
		Long totalSecond) {
		this.seq = seq;
		this.id = id;
		this.nickname = nickname;
		this.email = email;
		this.password = password;
		this.catSkin = catSkin;
		this.singleTheme = singleTheme;
		this.totalSecond = totalSecond;
	}

	public void updateUser(String nickname, String email){
		this.nickname = nickname;
		this.email = email;
	}

	public void updatePassword(String password) {
		this.password = password;
	}

	public void changeCatSkin(Byte no) {
		this.catSkin = no;
	}

	public void updatePoint(Integer plusValue) {
		this.point += plusValue;
		System.out.println("포인트업뎃완료");
		// 최대 포인트 이상이 되면 멤버십 정보 갱신
		while (this.point >= this.maxPoint) {
			updateMembershipLevel(this.membershipLevel);
		}
	}

	public void updateMembershipLevel(String membershipLevel) {
		// 다음 레벨 정보
		HashMap<String, String> nextLevelMap = new HashMap<>();
		nextLevelMap.put("BRONZE_LV1", "BRONZE_LV2");
		nextLevelMap.put("BRONZE_LV2", "BRONZE_LV3");
		nextLevelMap.put("BRONZE_LV3", "SILVER_LV1");
		nextLevelMap.put("SILVER_LV1", "SILVER_LV2");
		nextLevelMap.put("SILVER_LV2", "SILVER_LV3");
		nextLevelMap.put("SILVER_LV3", "GOLD_LV1");
		nextLevelMap.put("GOLD_LV1", "GOLD_LV2");
		nextLevelMap.put("GOLD_LV2", "GOLD_LV3");
		nextLevelMap.put("GOLD_LV3", "PLATINUM_LV1");
		nextLevelMap.put("PLATINUM_LV1", "PLATINUM_LV2");
		nextLevelMap.put("PLATINUM_LV2", "PLATINUM_LV3");
		nextLevelMap.put("PLATINUM_LV3", "DIAMOND_LV1");
		nextLevelMap.put("DIAMOND_LV1", "DIAMOND_LV2");
		nextLevelMap.put("DIAMOND_LV2", "DIAMOND_LV3");

		// 최대 경험치(초과 시 승급)
		HashMap<String, Integer> maxPointMap = new HashMap<>();
		maxPointMap.put("BRONZE_LV1", 50);
		maxPointMap.put("BRONZE_LV2", 100);
		maxPointMap.put("BRONZE_LV3", 200);
		maxPointMap.put("SILVER_LV1", 400);
		maxPointMap.put("SILVER_LV2", 800);
		maxPointMap.put("SILVER_LV3", 1600);
		maxPointMap.put("GOLD_LV1", 3200);
		maxPointMap.put("GOLD_LV2", 6400);
		maxPointMap.put("GOLD_LV3", 12800);
		maxPointMap.put("PLATINUM_LV1", 25600);
		maxPointMap.put("PLATINUM_LV2", 51200);
		maxPointMap.put("PLATINUM_LV3", 102400);
		maxPointMap.put("DIAMOND_LV1", 204800);
		maxPointMap.put("DIAMOND_LV2", 409600);
		maxPointMap.put("DIAMOND_LV3", Integer.MAX_VALUE);

		// 포인트 갱신 후 멤버십, 최대 포인트 갱신
		this.point -= maxPointMap.get(membershipLevel);
		membershipLevel = nextLevelMap.get(membershipLevel);
		this.membershipLevel = membershipLevel;
		this.maxPoint = maxPointMap.get(membershipLevel);

		System.out.println("멤버십 갱신 완료");
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		return Collections.singletonList("ROLE_USER").stream()
			.map(SimpleGrantedAuthority::new)
			.collect(Collectors.toList());
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.id;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
