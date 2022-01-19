package com.meme.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.meme.blog.model.User;

// DAO 역할
// 자동으로 bean등록이 된다
// @Repository 생략가능
public interface UserRepository extends JpaRepository<User, Integer>{

	// findAll 모든걸 리턴해라
	// save 인설트 업데이트 다가능
	
	
	
} // end interface
