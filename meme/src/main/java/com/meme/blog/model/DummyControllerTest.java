package com.meme.blog.model;

import java.util.List;
import java.util.function.Supplier;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.meme.blog.repository.UserRepository;


// html파일을 
@RestController
public class DummyControllerTest {
	
	// 의존성 주입
	@Autowired	// 자동 주입
	private UserRepository userRepository;
	
	// email, password
	@Transactional // save함수 대신 사용
	@PutMapping("/dummy/user/{id}")             
	public User updateUser(@PathVariable int id, @RequestBody User requestUser) {
		// @RequestBody json을 받기위한 어노테이션
		// json 데이터를 요청 => Java Object(MessageConverter의 Jackson라이브러리가 변환해서 받아줌
		
		System.out.println("id : " + id);
		System.out.println("password : " + requestUser.getPassword());
		System.out.println("email : " + requestUser.getEmail());
		
		User user = userRepository.findById(id).orElseThrow(()->{
			return new IllegalArgumentException("수정에 실패했습니다.");
		});

		user.setPassword(requestUser.getPassword());
		user.setEmail(requestUser.getEmail());

		// save 함수는 id를 전달하지 않으면 insert를 해주고
		// save 함수는 id를 전달하면 해당 id에 대한 데이터가 있으면 update를 해주고
		// save 함수는 id를 전달하면 해당 id에 대한 데이터가 없으면 insert를 한다
		// userRepository.save(user);
		
		// 더티 체킹
		return null;
	}
	
	@GetMapping("/dummy/users")
	public List<User> list(){
		return userRepository.findAll();
	} // list
	
	// 한페이지당 2건에 데이터를 리턴받아 볼 예정
	@GetMapping("/dummy/user")
	public List<User> pageList(@PageableDefault(size=2, sort="id", direction=Sort.Direction.DESC) Pageable pageable){
		Page<User> pagingUser = userRepository.findAll(pageable);
		
		List<User> users = pagingUser.getContent();
		return users;
	} // pageList
	
	// {id} 주소로 파라레터를 전달 받을 수 있음.
	// http://localhost:8000/blog/dummy/user/3
	@GetMapping("/dummy/user/{id}")
	private User detail(@PathVariable int id) {
		// user/4을 찾으면 내가 데이터베이스에서 못찾아 오게 되면 user가 null 이되니까
		// return null 이 되니까 프로그램에 문제가 생기니까
		// Optional로 너의 User객체를 감싸서 가져올테니 null인지 아닌지 판단해서 return해
		
		// 익명 객체
		// 람다식 
		User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
			@Override
			// findById Throw 옵션
			public IllegalArgumentException get() {
				
				return new IllegalArgumentException("해당 유저는 없습니다. id : " + id);
			}
		});
		/*User user = userRepository.findById(id).orElseGet(new Supplier<User>() {
			@Override
			public User get() {
				// 빈 객체를 user에 넣어주면 null은 아님
				return new User();
			}
		});*/
		// 요청 : 웹브라우저
		// user 객체 = 자바 오브젝트
		// 변환(웹브라우저가 이해할 수 있는 데이터) -> json(Gson 라이브러리)
		// 스브링부트 = MessageConverter라는 애가 응답시에 자동 작동
		// 만약에 자바 오브젝트를 리턴하게 되면  MessageConverter가 Jackson 라이브러리를 호출해서
		// user 오브젝트를 json으로 변환해서 브라우저에게 던져줍니다.
		return user;
	} // detail
	
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
