package com.meme.blog.test;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.meme.blog.model.Board;
import com.meme.blog.model.Reply;
import com.meme.blog.repository.BoardRepository;
import com.meme.blog.repository.ReplyRepository;

@RestController
public class ReplyControllerTest {
	
	@Autowired
	private BoardRepository boardRepository;
	
	@Autowired
	private ReplyRepository ReplyRepository;
	
	@GetMapping("/test/board/{id}")
	public Board getBoard(@PathVariable int id) {
		return boardRepository.findById(id).get();		// jackson 라이브러리 (오브젝트를 json으로 리턴) => 모델의 getter 호출
	} // getBoard
	
	@GetMapping("/test/reply")
	public List<Reply> getReply() {
		System.out.println("getReply 호출함");
		return ReplyRepository.findAll();
	} // getReply
	
	
} // ReplyControllerTest end class 
