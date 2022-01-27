package com.meme.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.meme.blog.model.Board;

/**	board 쿼리
 *  저장소 JPA 상속 및 쿼리 설정 가능
 * @author saha-vfx
 *
 */
// DAO 역할
// 자동으로 bean등록이 된다
// @Repository 생략가능
public interface BoardRepository extends JpaRepository<Board, Integer>{
 

	
} // end interface
