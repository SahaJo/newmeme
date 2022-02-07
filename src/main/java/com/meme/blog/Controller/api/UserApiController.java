package com.meme.blog.Controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.meme.blog.dto.ResponseDto;
import com.meme.blog.model.User;
import com.meme.blog.service.UserService;

/**
 * 	회원가입 컨트롤러
 * @author saha-vfx
 *
 */
@RestController // 데이터만 리턴할거임
public class UserApiController {
	
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
//	@Autowired
//	private HttpSession session;
	
//	@PostMapping("/api/user")
//	public int save(@RequestBody User user) {
//		System.out.println("UserApiController : save 호출됨 ");
//		return 1;
//	}
	
	
	@PostMapping("/auth/joinProc")
	public  ResponseDto<Integer> save(@RequestBody User user) {
		System.out.println("UserApiController : save 호출됨 ");
		// 실제로 DB에 insert를 하고 아래에서 return되면 완료.
	
//		int result = userSerive.회원가입(user);
		userService.회원가입(user);
				
//		return new ResponseDto<Integer>(HttpStatus.OK, result);
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1); //자바오브젝트를 jSON으로 변환해서 리턴(Jackson)
	} // save
	
	
		
	// 다른 방식으로 로그인할것 시큐리티를 사용함 
	// 기본 로그인 방식
//	@PostMapping("/api/user/login")													// HttpSession 세션 받기위해서 사용
//	public ResponseDto<Integer> login(@RequestBody User user /*HttpSession session*/){
//		System.out.println("UserApiController :  Login 호출됨");
//		User principal = userService.로그인(user);	// prinspal( 접근주체)
//		
//		if(principal != null) {
//			session.setAttribute("principal", principal);
//		} // if
//		
//		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
//	} // login
	
	@PutMapping("/user")
	public ResponseDto<Integer> update(@RequestBody User user){ // key=value, x-www-form-urlencoded
																// , @AuthenticationPrincipal PrincipalDetail principal, HttpSession session
		userService.회원수정(user);
		// 여기서는 트랜잭션이 종료되기 때문에 DB에 값은 변경이 됐음.
		// 하지만 세션값은 변경되지 않은 상태이기 때문에 우리가 직접 세션값을 변경해줄 것임.
		// 강제로 세셔 값 바꾸기
		/*		직접 넣어주는건 불가능함 
		 * Authentication autentication = new
		 * UsernamePasswordAuthenticationToken(principal, null,
		 * principal.getAuthorities()); SecurityContext securityContext =
		 * SecurityContextHolder.getContext();
		 * securityContext.setAuthentication(autentication);
		 * session.setAttribute("SPRING_SECURITY_CONTEXT", securityContext);
		 */
		// ==========================================================
		// 세션 등록
		Authentication authentication =
				authenticationManager.authenticate(
						new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	} // update
	
} // end class 
