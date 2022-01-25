package com.meme.blog.Controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.meme.blog.dto.ResponseDto;
import com.meme.blog.model.RoleType;
import com.meme.blog.model.User;
import com.meme.blog.service.UserService;

@RestController // 데이터만 리턴할거임
public class UserApiController {
	
	@Autowired
	private UserService userService;
	

	
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
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
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
	
} // end class 
