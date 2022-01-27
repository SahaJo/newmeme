package com.meme.blog.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.meme.blog.model.Board;
import com.meme.blog.model.User;
import com.meme.blog.repository.BoardRepository;

/**
 *  트랜잭션 설정
 *   ex) 암호 해쉬화
 * @author saha-vfx
 *
 */
@Service
public class BoardService {
	
	
		@Autowired
		private BoardRepository boardRepository;
		
		
		@Transactional	// import org.springframework.transaction.annotation.Transactional;
		public void 글쓰기(Board board, User user) {	// title, content
			board.setCount(0);			// 조회수 직접 설정
			board.setUser(user);
			boardRepository.save(board);
		
		} // 회원가입
		
		@Transactional(readOnly = true)
		public Page<Board> 게시글(Pageable pageable){
			return boardRepository.findAll(pageable);
		} // 게시글
		
		@Transactional(readOnly = true)
		public Board 글상세보기(int id) {
			return boardRepository.findById(id)
					.orElseThrow(()->{
						return new IllegalArgumentException("글 상세보기 실패: 아이디를 찾을 수 없습니다.");
					});
		}// 글상세보기
		
		@Transactional
		public void 글삭제하기(int id) {
			boardRepository.deleteById(id);
				
		} // deleteById
		
} // end class
