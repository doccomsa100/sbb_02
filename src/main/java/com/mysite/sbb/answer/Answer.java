package com.mysite.sbb.answer;

import java.time.LocalDateTime;

import com.mysite.sbb.question.Question;
import com.mysite.sbb.user.SiteUser;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

// 엔티티클래스 : 테이블로 생성된다.  용도>답변테이블

@Getter
@Setter
@Entity
public class Answer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(columnDefinition = "TEXT")
	private String content;
	
	private LocalDateTime createDate;
	
	// 답변글을 통한 질문글을 참조.
	@ManyToOne
	private Question question;  // 질문데이타는 1개이므로 List컬렉션 사용안함
	
	// 답변글 작성자.  한 사용자가 여러개 게시글작성(1:N)
	@ManyToOne
	private SiteUser author;
	
}
