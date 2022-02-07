package com.meme.blog.test;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

// 
@RestController
public class HttpControllerTest {
	private static final String TAG= "HttpControllerTest : ";
	
	@GetMapping("/http/lombok")
	public String lombokTest() {
		
//		Member m1 = new Member(1,"aaaa","12341","emai@a.a");
		// Builder를 쓰면 생성자의 순서를 지킬 필요가 없음 내가 원하는 순서로 넣을 수 있음
		Member m1 = Member.builder().username("sess").passwd("123").email("ss@as1.23").build();
		System.out.println(TAG+"getter : " + m1.getUsername());
		m1.setUsername("??????");
		System.out.println(TAG+"setter : " + m1.getUsername());
		return "Lomboktest 완료";
		
	} // lombokTest
	
	
	
	
	// 인터넷 브라우저 요청은 무조건 get요청밖에 할 수 없다.
	// localhost:8080/http/get
	@GetMapping("/http/get")
	public String getTest(Member m) {
		return "get 요청";	
	} // getTest
	
	// localhost:8080/http/get
	@GetMapping("/http/get1")
	public String getTest1(Member m) {
	return "get 요청  \\\\" +"\n id :"+ m.getId() +"\n username :  " +m.getUsername() + "\n passwd: " + m.getPasswd() + "\n email : "+ m.getEmail();	
	
	} // getTest
	
	// localhost:8080/http/post
	@PostMapping("http/post")
	public String postTest(Member m) {
		return "post 요청  \\\\" +"\n id :"+ m.getId() +"\n username :  " +m.getUsername() + "\n passwd: " + m.getPasswd() + "\n email : "+ m.getEmail();	
	} // postTest
	
	// localhost:8080/http/post
		@PostMapping("http/post1")
	//	public String postTest1(@RequestBody String text) {   // RequestBody 를 raw파일로 보냈다는건 text/plain 낮은 장벽
		public String postTest1(@RequestBody Member m) {   //@RequestBody 안붇이면 널뜸 object로 맵핑해서 받음
			return "post 1 요청  "+ "\n id :"+ m.getId() +"\n username :  " +m.getUsername() + "\n passwd: " + m.getPasswd() + "\n email : "+ m.getEmail();	
		} // postTest
		
	
	// localhost:8080/http/put
	@PutMapping("http/put")
	public String putTest(@RequestBody Member m) {
	return "put 요청"+ "\n id :"+ m.getId() +"\n username :  " +m.getUsername() + "\n passwd: " + m.getPasswd() + "\n email : "+ m.getEmail();	
	} // putTest
	
	// localhost:8080/http/delete
	@DeleteMapping("http/delete")
	public String deleteTest() {
	return "delete 요청";	
	} // deleteTest
}
