package com.meme.blog.Controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.meme.blog.config.auth.PrincipalDetail;
import com.meme.blog.dto.ResponseDto;
import com.meme.blog.model.Board;
import com.meme.blog.service.BoardService;

/**
 * 	글쓰기 컨트롤러
 * @author saha-vfx
 *
 */
@RestController
public class BoardApiController {
	
	@Autowired
	private BoardService boardService;
	
	
	@PostMapping("/api/board")
	public  ResponseDto<Integer> save(@RequestBody Board board, @AuthenticationPrincipal PrincipalDetail principal) {
		System.out.println("BoardApiController : 글쓰기save 호출됨 ");
		boardService.글쓰기(board, principal.getUser());
				
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	} // save
	
	
	@DeleteMapping("/api/board/{id}")
	public ResponseDto<Integer> deleteById(@PathVariable int id){
		System.out.println("글삭제하기 id : " + id);
		boardService.글삭제하기(id);
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	} // deleteById
	
	
	
	
	
	
	
	// 다른 방식으로 로그인할것 시큐리티를 사용함 
	// 기본 로그인 방식
//	@PostMapping("/api/user/login")													// HttpSession 세션 받기위해서 사용
//	public ResponseDto<Integer> login(@RequestBody User user /*HttpSession session*/){
//		System.out.println("UserApiController :  Login 호출됨");
//		User principal = userService.로그인(user);	// prinspal( 접근주체)
//		
//		if(principal != null) {
//			session.setAttribute("principal", principal);
//		} // if
//		
//		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
//	} // login
	
} // end class 
