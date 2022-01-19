package com.meme.blog.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meme.blog.repository.UserRepository;

@RestController
public class DummyControllerTest {
	
	// 의존성 주입
	@Autowired	// 자동 주입
	private UserRepository userRepository;
	
	
	// http://localhost:8000/blog/dummy/join (요청)
	// http의 body에 username, password, email 데이터를 가지고(요청)
	@PostMapping("/dummy/join")
//	public String join(String username, String passwoard, String email) {	// key = value (약속된 규칙)
	public String join(User user) {	// key = value (약속된 규칙)
		System.out.println("id : " + user.getId());
		System.out.println("username : " + user.getUsername());
		System.out.println("passwoard : " + user.getPassword());
		System.out.println("email : " + user.getEmail());
		System.out.println("role : " + user.getRole());
		
		// interface를 통해 (DAO) 바로 DB에 저장
		user.setRole(RoleType.USER);
		userRepository.save(user);
		return "회원가입이 완료되었습니다.";
	} //join
	
} // end class
