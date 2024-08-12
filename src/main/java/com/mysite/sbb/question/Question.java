package com.mysite.sbb.question;

import java.time.LocalDateTime;
import java.util.List;

import com.mysite.sbb.answer.Answer;
import com.mysite.sbb.user.SiteUser;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;


// 엔티티클래스 : 테이블로 생성된다. 용도>질문테이블

@Getter
@Setter  // 엔티티를 만들때 Setter메서드는 사용하지 않는다.
@Entity
public class Question {

	@Id  // primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(length = 200)
	private String subject;
	
	@Column(columnDefinition = "TEXT")
	private String content;
	
	private LocalDateTime createDate;
	
	// 질문글을 통한 답변글을 참조.
	// 질문글을 삭제하면, 답변글도 함께 삭제.  cascade = CascadeType.REMOVE
	@OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE)
	private List<Answer> answerList; // 답변이 여러개이므로 List<Answer>
	
	// 질문글 작성자.  한 사용자가 여러개 게시글작성(1:N)
	@ManyToOne
	private SiteUser author;
	
}
