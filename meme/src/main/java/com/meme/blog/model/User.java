package com.meme.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
// ORM -> Java(다른언어) Object 테이블로 매핑해주는 기술
@Entity	// User 클래스가 MySQL에 테이블이 생성이 된다.
// @DynamicInsert // insert 시에 null 값을 제외한 값을 전송
public class User {

	@Id //Primary Key
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 프로젝트에서 연결된 DB의 넘버링 전략을 따라간다.
	private int id; // 시퀀스, auto_increment
	
	@Column(nullable = false, length = 30)	// 널포인트 안됨
	private String username; //아이디
	
	@Column(nullable = false, length = 100)	/// hashcode (비밀번호 암호화)
	private String password;
	
	@Column(nullable = false, length = 50)
	private String email;
	
	
//	@ColumnDefault("'user'") 		// " '글자'	" ********************************
	@Enumerated(EnumType.STRING) // DB는 RoleType이라는게 없다 그래서 DB에 String이라고 알려주어야함
	private RoleType role; // Enum을 쓰는게 좋음 // 롤 admin, user, manager 권한 같은거 부여
									// 도메인 설정이 가능함(범위가 정해졌다)
	
	@CreationTimestamp // 시간 자동 입력
	private Timestamp createDate;
	
} // end class
