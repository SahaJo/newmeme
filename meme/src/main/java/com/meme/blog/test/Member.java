package com.meme.blog.test;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//@Getter
//@Setter
@Data // getter + setter
//@RequiredArgsConstructor   // 파이널에 관한 생성자
@NoArgsConstructor // 빈생성자
//@AllArgsConstructor // 모든 생성자
public class Member {
	private  Integer id;
	private  String username;
	private  String passwd;
	private  String email;
//	
	@Builder
	public Member(Integer id, String username, String passwd, String email) {
		this.id = id;
		this.username = username;
		this.passwd = passwd;
		this.email = email;
	}
//	
//	public Integer getId() {
//		return id;
//	}
//	public void setId(Integer id) {
//		this.id = id;
//	}
//	public String getUsername() {
//		return username;
//	}
//	public void setUsername(String username) {
//		this.username = username;
//	}
//	public String getPasswd() {
//		return passwd;
//	}
//	public void setPasswd(String passwd) {
//		this.passwd = passwd;
//	}
//	public String getEmail() {
//		return email;
//	}
//	public void setEmail(String email) {
//		this.email = email;
//	}

	
	
} // end class
