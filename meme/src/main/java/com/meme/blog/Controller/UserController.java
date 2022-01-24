package com.meme.blog.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

	@GetMapping("/user/joinForm")
	public String joinForm() {
		return "user/joinForm";
	} // joinForm
	
	@GetMapping("/user/loginForm")
	public String loginForm() {
		return "user/loginForm";
	} // joinForm
	
} // end class
