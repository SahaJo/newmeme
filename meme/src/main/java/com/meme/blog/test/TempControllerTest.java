package com.meme.blog.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TempControllerTest {

	@GetMapping("/temp/home")
	public String tempHome() {
		System.out.println("end hom.html");
		return "/home.html";
	} // tempHome
	
	@GetMapping("/temp/img")
	public String tempImg() {
		System.out.println("찾았다");
		return "/a.jpg";
	}
	
	@GetMapping("/temp/jsp")
	public String tempTest() {
		System.out.println("찾았다");
		return "/test";
	}
} // end class
