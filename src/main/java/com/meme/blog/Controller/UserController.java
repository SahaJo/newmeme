package com.meme.blog.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.meme.blog.config.auth.PrincipalDetail;
import com.meme.blog.model.KakaoProfile;
import com.meme.blog.model.OAuthToken;
import com.meme.blog.model.User;
import com.meme.blog.service.UserService;

@Controller
public class UserController {

	// yml파일에서 키를 만듬
	@Value("${cos.key}")
	private String cosKey;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
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
	public String kakaoCallback(String code) { // data를 리턴해주는 컨트롤러 함수
		
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
		
		// Gson, Json Simple, ObjectMapper
		ObjectMapper objectMapper = new ObjectMapper();
		OAuthToken oauthToken = null;
		try {
			oauthToken = objectMapper.readValue(response.getBody(), OAuthToken.class);
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} // try-catch
		
		System.out.println("카카오 엑세스 토큰 : " + oauthToken.getAccess_token());
		System.out.println("==================================");
		
		RestTemplate rt2 = new RestTemplate();
		
		// HttpHeader 오브젝트생성
		HttpHeaders headers2 = new HttpHeaders();
		headers2.add("Authorization", "Bearer " +oauthToken.getAccess_token() );
		headers2.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
			
		// headers 값을 지닌 엔티티가 됨 .exchange()함수를 갖기위해 엔티티 사용
		// HttpHeader와 HttpBody를 하나의 오브젝트에 담기
		HttpEntity<MultiValueMap<String, String>> kakaoProfileRequest2 = 
				new HttpEntity<>(headers2);
		
		// Http 요청하기 -Post방식 - response 변수의 응답 받음
		ResponseEntity<String> response2 = rt2.exchange(
				"https://kapi.kakao.com/v2/user/me"
				, HttpMethod.POST
				,kakaoProfileRequest2
				,String.class				
		);
//		System.out.println("response2 : " + response2);
		
		ObjectMapper objectMapper2 = new ObjectMapper();
		KakaoProfile kakaoProfile = null;
		try {
			kakaoProfile = objectMapper2.readValue(response2.getBody(), KakaoProfile.class);
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} // try-catch
		
		// User 오브젝트 : username, password, email
		System.out.println("카카오 아이디(번호) : " + kakaoProfile.getId());
		System.out.println("카카오 이메일 : " + kakaoProfile.getKakao_account().getEmail());
		
		System.out.println("블로그서버 유저네임 : " + kakaoProfile.getKakao_account().getEmail() + "_" + kakaoProfile.getId() );
		System.out.println("블로그서버 이메일 : " + kakaoProfile.getKakao_account().getEmail());
		System.out.println("블로그서버 패스워드 : " + cosKey);
		
		// UUID -> 중복되지 않는 어떤 특정 값을 만들어내는 알고리즘
		System.out.println("====================================================================");
		
		User kakaoUser = User.builder()
				.username(kakaoProfile.getKakao_account().getEmail()+"_"+kakaoProfile.getId())
				.password(cosKey)
				.email(kakaoProfile.getKakao_account().getEmail())
				.oauth("kakao")
				.build();
		
		// 가입자 혹은 비가입자 체크 해서 처리
		User originUser = userService.회원찾기(kakaoUser.getUsername());
		
		if(originUser.getUsername() == null) {
			System.out.println("기존 회원이 아니기에 자동 회원가입을 진행합니다");
			userService.회원가입(kakaoUser);
		} // 아이디가 없으면 자동 가입
		
		System.out.println("자동 로그인을 진행합니다.");
		//  로그인 처리
		Authentication authentication =
				authenticationManager.authenticate(
						new UsernamePasswordAuthenticationToken(kakaoUser.getUsername(), cosKey));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
//		return response2.getBody();
		return "redirect:/" ;
		
	} // kakaoCallback
	
} // end class
