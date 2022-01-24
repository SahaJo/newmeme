package com.meme.blog.Controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
	private UserService userSerive;
	
	
//	@PostMapping("/api/user")
//	public int save(@RequestBody User user) {
//		System.out.println("UserApiController : save 호출됨 ");
//		return 1;
//	}
	
	
	@PostMapping("/api/user")
	public  ResponseDto<Integer> save(@RequestBody User user) {
		System.out.println("UserApiController : save 호출됨 ");
		// 실제로 DB에 insert를 하고 아래에서 return되면 완료.
		user.setRole(RoleType.USER);
//		int result = userSerive.회원가입(user);
		userSerive.회원가입(user);
				
//		return new ResponseDto<Integer>(HttpStatus.OK, result);
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	} // save
	
} // end class 
