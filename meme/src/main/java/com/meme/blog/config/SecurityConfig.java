package com.meme.blog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.meme.blog.config.auth.PrincipalDetailService;


/**
 *  Security Config 기능 설정, 관리
 *  해쉬 엔코더 기능 추가
 * @author saha-vfx
 *
 */
// 빈 등록 : 스프링 컨테이너에서 객체를 관리할 수 있게 하는 것
									// 시큐리티 필터 추가 = 스프링 시큐리티가 활성화가 되어있는데
									// 어떤 설정을 해당(이곳) 파일에서 하겠다.
@EnableWebSecurity	// 시큐리티 피렅가 등록된다.
@Configuration			// 빈등록(IoC관리)
@EnableGlobalMethodSecurity(prePostEnabled = true)	// 특정 주소로 접근을하면 권한 및 인증을 미리 체크하겟다는 뜻.
public class SecurityConfig extends WebSecurityConfigurerAdapter  {
	
	@Autowired
	private PrincipalDetailService principalDetailService;
	
	@Bean // IoC 등록
	public BCryptPasswordEncoder encodePWD() {
//		String encPassword = new BCryptPasswordEncoder().encode("1234");
		return new BCryptPasswordEncoder();		// 스프링이 관리하게 됨
	} // encodePWD
	
	
	// 시큐리티가  대신 로그인해주는데 password를 가로채기를 하는데
	// 해당 password가 뭘로 해쉬가 되어 회우언가입이 되었는지 알아야
	// 같은해쉬로 암호화해서 DB에 있는 해쉬랑 비교할 수 있음.
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// 패스워드 비교를 위해 꼭 만들어줘야 함
		auth.userDetailsService(principalDetailService).passwordEncoder(encodePWD());
	} // configure(AuthenticationManagerBuilder)
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// 49강에서 설명
		http
			.csrf().disable()	//  csrf 토큰 비활성화 (테스트시 걸어두는 게 좋음)
			.authorizeHttpRequests()
				.antMatchers("/", "/auth/**", "/js/*", "/css/**", "/image/**", "/dummy/**")
				.permitAll()
				.anyRequest()
				.authenticated()
			.and()
				.formLogin()		// 인증이 필요하면 바로 뜨게 만듬
				.loginPage("/auth/loginForm") // 이 페이지로 
				.loginProcessingUrl("/auth/loginProc")	// 스프링 시큐리티가 해당주소로 요청오는 로그인을 가로채서 대신 로그인.
				.defaultSuccessUrl("/") 	// 정상적으로 요청이 완료가 되면 이 주소로 이동
				;	
	} // configure

} // end class
