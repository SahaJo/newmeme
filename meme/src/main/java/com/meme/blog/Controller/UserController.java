package com.meme.blog.Controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

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
	
	@GetMapping("/auth/kakao/callback")
	public @ResponseBody String kakaoCallback(String code) { // data를 리턴해주는 컨트롤러 함수
		
		// POST방식으로 key=value 데이터를 요청 (카카오쪽으로)
		// Retrofit2 android
		// Okhttp
		// RestTemplate
		
		RestTemplate rt = new RestTemplate();
		
		// HttpHeader 오브젝트생성
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
	
		// HttpBody 오브젝트생성
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("grant_type", "authorization_code");
		params.add("client_id", "26d3e56505f8dfe90f664568bbe848b6");
		params.add("redirect_uri", "http://localhost:8000/auth/kakao/callback");
		params.add("code", code );
		
		// headers 값을 지닌 엔티티가 됨 .exchange()함수를 갖기위해 엔티티 사용
		// HttpHeader와 HttpBody를 하나의 오브젝트에 담기
		HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest = 
				new HttpEntity<>(params,headers);
		
		// Http 요청하기 -Post방식 - response 변수의 응답 받음
		ResponseEntity<String> response = rt.exchange(
				"https://kauth.kakao.com/oauth/token"
				, HttpMethod.POST
				,kakaoTokenRequest
				,String.class				
		);
		
		return "카카오 토큰 요청 완료: 토큰요청에 대한 응답 : " + response.getBody();
		
	} // kakaoCallback
	
} // end class
