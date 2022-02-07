package com.meme.blog.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.meme.blog.model.Board;
import com.meme.blog.model.Reply;
import com.meme.blog.model.User;
import com.meme.blog.repository.BoardRepository;
import com.meme.blog.repository.ReplyRepository;

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
		
		@Autowired
		private ReplyRepository replyRepository;
		
		
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
		
		@Transactional
		public void 글수정하기(int id, Board requestBoard) {
			Board board = boardRepository.findById(id)
					.orElseThrow(()->{
						return new IllegalArgumentException("글찾기 실패 : 아이디를 찾을 수 없습니다.");
					});
			board.setTitle(requestBoard.getTitle());
			board.setContent(requestBoard.getContent());
			// 해당 함수로 종료시 (트랜잭션이 Service가 종료 될 때) 트랜잭션이 종료됩니다.
			// 이때 더티체킹 - 자동 업데이트가 됨 DB flush
		} // 글수정하기

		@Transactional
		public void 댓글쓰기(User user, int boardId, Reply requestReply) {
			
			Board board =	boardRepository.findById(boardId)
					.orElseThrow(()->{
						return new IllegalArgumentException("댓글 쓰기 실패. : 게시글 아이디를 찾을 수 없습니다.");
					});
			
			requestReply.setUser(user);
			requestReply.setBoard(board);
			
			replyRepository.save(requestReply);
				
		} // 댓글쓰기
		
} // end class
