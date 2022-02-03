package com.meme.blog.Controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.meme.blog.config.auth.PrincipalDetail;

@Controller
public class UserController {

	// 인증이 안된 사용자들이 출입할 수 있는 경로를 /auth/** 허용
	// 그냥 주소가 / 이면 index.jsp 허용
	// 나머지는 불허용 static이하에 있는 /js/*.*, /css/**, /image/**/
	
//	@GetMapping("/user/joinForm")
	@GetMapping("/auth/joinForm")
	public String joinForm() {
		return "user/joinForm";
	} // joinForm
	
	// 로그인
//	@GetMapping("/user/loginForm")
	@GetMapping("/auth/loginForm")
	public String loginForm() {
		return "user/loginForm";
	} // joinForm
	
	// 회원정보 수정
	@GetMapping("/user/updateForm")
	public String updateForm(@AuthenticationPrincipal PrincipalDetail principal) {
		//  updateForm(@AuthenticationPrincipal PrincipalDetail principal) 는 AuthenticationFilter를 거쳐서 
		// usernamePassword Auth 토큰을 만듬 Auth manager가 객체를 만듬
		return "user/updateForm";
	} // updateForm
	
} // end class
