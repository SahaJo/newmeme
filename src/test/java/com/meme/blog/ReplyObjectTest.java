package com.meme.blog;

import org.junit.jupiter.api.Test;

import com.meme.blog.model.Reply;

public class ReplyObjectTest {
	
	@Test
	public void 투스트링테스트() {
		Reply reply = Reply.builder()
				.id(1)
				.user(null)
				.board(null)
				.content("dddd")
				.build();
		
		System.out.println(reply); // 오브젝트 출력시에 toString()이 자동호출됨
	}

}
