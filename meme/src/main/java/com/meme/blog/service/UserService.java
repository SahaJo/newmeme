package com.meme.blog.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.meme.blog.model.RoleType;
import com.meme.blog.model.User;
import com.meme.blog.repository.UserRepository;

// 스프링이 컴포넌트 스캔을 통해서 Bean에 등록을 해줌. IoC를 해준다.
// service가 왜 필요한지
// 1. 트랜잭션 관리
// 2. 서비스 의미 때문에
// 예) 송금서비스 사람1 --> 사람2 송금
//  (1) 사람1 금액 update and commit
//	 (2) 사람2 금액 update and commit
// -- 한개 또는 다수의 서비스가 될 수 있음 여러개 중 한개의 서비스가 고장나면 롤백이 필요함 그래서 서비스가 필요함

/**
 *  트랜잭션 설정
 *   ex) 암호 해쉬화
 * @author saha-vfx
 *
 */
@Service
public class UserService {
	
	// UserRepository 인터페이스의 함수를 가져와 사용
		@Autowired
		private UserRepository userRepository;
		
		@Autowired
		private BCryptPasswordEncoder encoder;
		

		
		@Transactional	// import org.springframework.transaction.annotation.Transactional;
		public void 회원가입(User user) {
			String rawPassword = user.getPassword();	// 1234 원문
			String encPassword = encoder.encode(rawPassword); // 해쉬
			user.setPassword(encPassword);
			user.setRole(RoleType.USER);
			userRepository.save( user);
		} // 회원가입
		
		// security 사용할거임 
//		@Transactional(readOnly = true) // SELECT 할 때 트랜잭션 시작, 서비스 종료시에 트랜잭션 종료(정합성)
//		public User 로그인(User user) { 
//			return userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
//		}
		
//		@Transactional
//		public int 회원가입(User user) {
//			try {
//				userRepository.save( user);
//				return 1;
//			} catch (Exception e) {
//				e.printStackTrace();
//				System.out.println("UserService : 회원가입(): " + e.getMessage());
//			} // try-catch
//			return -1;
//		} // 회원가입
		
		@Transactional
		public void 회원수정(User user) {
			// 수정시에는 Jpa(영속성 컨텍스트) User 오브젝트를 영속화시키고, 영속화된 User 오브젝트를 수정
			// Select를 해서 User오브젝트를 DB로 부터 가져오는 이유는 영속화를 하기위해서!!
			// 영속화된 오브젝트를 변경하면 자동으로 DB에 update문을 날려준다.
			User persistance = userRepository.findById(user.getId()).orElseThrow(()->{
				return new IllegalArgumentException("회원찾기 실패");
			});
			String rawPassword = user.getPassword();
			String encPassword = encoder.encode(rawPassword);
			persistance.setPassword(encPassword);
			persistance.setEmail(user.getEmail());
			// 회원수정 함수 종료시 = 서비스 종료 = 트랜잭션 종료 = commit이 자동으로 됩니다.
			// 영속화된 persistance 객체의 변화가 감지되면 더티체킹이 되어 update문을 날려줌.
			
		} // 회원수정
		
} // end class
