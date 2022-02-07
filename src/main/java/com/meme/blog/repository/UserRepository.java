package com.meme.blog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.meme.blog.model.User;

/**	User 쿼리
 *  저장소 JPA 상속 및 쿼리 설정 가능
 * @author saha-vfx
 *
 */
// DAO 역할
// 자동으로 bean등록이 된다
// @Repository 생략가능
public interface UserRepository extends JpaRepository<User, Integer>{

	// SELECT * FROM user WHERE username = 1?;
	Optional<User> findByUsername(String username);
	
	// findAll 모든걸 리턴해라
	// save 인설트 업데이트 다가능
	
	// JPA Naming 쿼리
	// select * from user where user = ? AND password = ?;
	// security 사용할거임
	//User findByUsernameAndPassword(String username, String password);
	
	// 위와 같은 기능
//	@Query(value="SELECT * FROM user WHERE username =?1 AND password = ?2", nativeQuery = true)
//	User login(String username, String password);
	
} // end interface
