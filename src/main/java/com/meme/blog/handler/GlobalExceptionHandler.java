package com.meme.blog.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.meme.blog.dto.ResponseDto;

@ControllerAdvice // 모든 컨트롤러가 에러가 걸렸을경우 잡기
@RestController
public class GlobalExceptionHandler {

	@ExceptionHandler(value = Exception.class)
//	public String handlerArgumentException(IllegalArgumentException e) {
	public ResponseDto<String> handlerArgumentException(Exception e) {
		return new ResponseDto<String>(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
//		return "<h1>" + e.getMessage() + "</h1>";
	} // handleArgumentException
	
	
//	@ExceptionHandler(value = Exception.class)
//	public String handleArgumentException(Exception e) {
//		return "<h1> ex" + e.getMessage() + "</h1>";
//	} // handleArgumentException
	
} // end class
