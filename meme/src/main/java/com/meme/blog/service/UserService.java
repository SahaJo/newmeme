package com.meme.blog.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

@Service
public class UserService {
	
	// UserRepository 인터페이스의 함수를 가져와 사용
		@Autowired
		private UserRepository userRepository;
		
		@Transactional	// import org.springframework.transaction.annotation.Transactional;
		public void 회원가입(User user) {
			userRepository.save( user);
		} // 회원가입
		
		@Transactional(readOnly = true) // SELECT 할 때 트랜잭션 시작, 서비스 종료시에 트랜잭션 종료(정합성)
		public User 로그인(User user) { 
			return userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
		}
		
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
} // end class
