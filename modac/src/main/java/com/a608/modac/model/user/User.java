package com.a608.modac.model.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;

@Getter
@Entity
@ToString
@DynamicInsert	//not null인 컬럼에 데이터 저장 안해줄때 자동으로 default값으로 매핑해줌
@NoArgsConstructor
@Table(name = "users")
public class User {

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

	@Column(name = "single_theme")
	private String singleTheme;    //싱글룸 테마(우주....)

	@Column(name = "total_second")
	private Long totalSecond; //인생 총 공부시간

	@Column(name = "membership_grade")
	private String membershipGrade;

	@Builder
	public User(Long seq, String id, String nickname, String email, String password, String singleTheme, Long totalSecond, String membershipGrade) {
		this.seq = seq;
		this.id = id;
		this.nickname = nickname;
		this.email = email;
		this.password = password;
		this.singleTheme = singleTheme;
		this.totalSecond = totalSecond;
		this.membershipGrade = membershipGrade;
	}

	public void updateUser(String nickname, String email){
		this.nickname = nickname;
		this.email = email;
	}

	public void updatePassword(String password){
		this.password = password;
	}

}
