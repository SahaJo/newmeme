package com.meme.blog.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.meme.blog.service.BoardService;

@Controller
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	
	// @AuthenticationPrincipal import core
	// 시큐리티 세션에 접근 할때 사용하는 어노테이션  @AuthenticationPrincipal
	@GetMapping({"","/"})
//	public String index(@AuthenticationPrincipal PrincipalDetail principal ) {		// 로그인값을 받아 보고 싶을때 
	// model은 request정보를 담음 view까지 들고 이동
	public String index(Model model,
				@PageableDefault(size=3, sort="id", direction = Sort.Direction.DESC) Pageable pageable) {	
									// for(string board : boardsService.게시글()){}
		model.addAttribute("boards",boardService.게시글(pageable));	//viewResolver 작동
		
		return "index";
	}
	
	@GetMapping("/board/saveForm")
	public String saveForm() {
		return "board/saveForm";
	}
	
} // end class
