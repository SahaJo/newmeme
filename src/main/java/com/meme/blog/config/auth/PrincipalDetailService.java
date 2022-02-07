package com.meme.blog.config.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.meme.blog.model.User;
import com.meme.blog.repository.UserRepository;


@Service  // Bean 등록
public class PrincipalDetailService implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepository;
	
	// spring이 로그인 요청을 가로챌 때, username, password 변수 2개를 가로채는데
	// password 부분 처리는 알아서함.
	// username이 DB에 있는지만 확인해주면 됨.
	// loadUserByUsername 확인하는 함수임
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User principal = userRepository.findByUsername(username)
				.orElseThrow(()->{
					return new UsernameNotFoundException("해당 사용자를 찾을 수 없습니다. : " + username );
				});
		
		return new PrincipalDetail(principal);	// 시큐리티의 세션에 유저 정보가 저장이 됨.
	} // loadUserByUsername

}
