package com.meme.blog.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Board {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false, length = 100)
	private String title;
	
	@Lob // 데용량 데이터
	private String content;
	
//	@ColumnDefault("0")
	private int count; // 조회수
							
							// ManyToOne (___) 기본값
	@ManyToOne(fetch = FetchType.EAGER)		// Many = Board, User = One 여러개의 게시글은 한명이 여러개를 쓸 수 있다.
	@JoinColumn(name="userId")	// 자동으로 FK로 만들어짐
	private User user;	// DB는 오브젝트를 저장할 수 없다. FK, 자바는 오브젝트를 저장할 수 있다.
									// 테이블은 폴인키로 인식한다
	
	// @JoinColumn(name=replyId)를 가져올 필요가 없다 1 정규화가 사라짐(원자성)(데이터베이스에 만들어지면 안됨)
//	private Reply reply; 1개만 들고 오면 안됨 몇 개일지 알수가 없음
						// Reply 필드 위에있는 변수 명을 넣어 주면 됨 (fetch = FetchType.LAZY) @OneToMany 기본값	EAGER바꿈
	@OneToMany(mappedBy = "board", fetch = FetchType.EAGER)	// 하나의 게시글에 여러개의 댓글이 달림   앞이 테이블 뒤가 가져오는거 (값을 가져오기 위해 사용)
	@JsonIgnoreProperties({"board"}) //reple 안에서 board 호출이안됨 방법이 많음
	private List<Reply> replys;						// mappedBy 연관관계의 주인이 아니다.(난 FK가 아니에요) DB에 칼럼을 만들지 마세요
	
	
	@CreationTimestamp
	private Timestamp createDate;

} // end class
