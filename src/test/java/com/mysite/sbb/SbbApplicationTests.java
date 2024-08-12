package com.mysite.sbb;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.mysite.sbb.answer.Answer;
import com.mysite.sbb.answer.AnswerRepository;
import com.mysite.sbb.question.Question;
import com.mysite.sbb.question.QuestionRepository;
import com.mysite.sbb.question.QuestionService;

@SpringBootTest  // 스프링부트의 테스트용 클래스라는 것을 의미
class SbbApplicationTests {

	@Autowired  // 롬복 어노테인션 사용하면 안됨.
	private QuestionRepository questionRepository;
//	
//	@Autowired
//	private AnswerRepository answerRepository;
	
	@Autowired
	private QuestionService questionService;
	
	@Transactional
	@Test  // 아래 메서드는 JUnit 환경에서 테스트하기위한 메서드이다.
	void testJpa() {
		/*
		1)
		Question q1 = new Question();
		q1.setSubject("sbb가 무엇인가요?");
		q1.setContent("sbb에 대해서 알고 싶습니다.");
		q1.setCreateDate(LocalDateTime.now());
		this.questionRepository.save(q1); // 첫번째 질문데이타 저장. 
		
		Question q2 = new Question();
		q2.setSubject("스프링부트 모델 질문입니다.");
		q2.setContent("id는 자동으로 생성되나요?");
		q2.setCreateDate(LocalDateTime.now());
		this.questionRepository.save(q2); // 두번째 질문데이타 저장. 
		
		2)
		List<Question> all = this.questionRepository.findAll();
		assertEquals(2, all.size());  //  assertEquals(기대값, 실제값)
		
		Question q = all.get(0);
		assertEquals("sbb가 무엇인가요?", q.getSubject());
		
		3)
		Optional<Question> oq = this.questionRepository.findById(3);
		
		if(oq.isPresent()) {
			Question q = oq.get();
			assertEquals("sbb가 무엇인가요?", q.getSubject());
		}
		
		4)
		Question q = this.questionRepository.findBySubject("sbb가 무엇인가요?");
		assertEquals(3, q.getId());
		
		5)
		Question q = this.questionRepository.findBySubjectAndContent("sbb가 무엇인가요?", "sbb에 대해서 알고 싶습니다.");
		assertEquals(3, q.getId());
		6)
		Optional<Question> oq = this.questionRepository.findById(3);
		assertTrue(oq.isPresent()); // false 반화되면, 에러발생
		Question q = oq.get(); // 수정하고자 하는 데이타를 참조.
		q.setSubject("수정한 제목");
		this.questionRepository.save(q); // 삽입(insert), 수정(update)시 save()메서드 사용
		
		7)
		assertEquals(2, this.questionRepository.count());
		Optional<Question> oq = this.questionRepository.findById(3);
		assertTrue(oq.isPresent());
		Question q = oq.get();
		this.questionRepository.delete(q);
		assertEquals(1, this.questionRepository.count());
		
		8)
		Optional<Question> oq = this.questionRepository.findById(4);
		assertTrue(oq.isPresent());
		Question q = oq.get(); // 질문글 참조.
		
		Answer a = new Answer();
		a.setContent("네 자동으로 생성됩니다.2");
		a.setQuestion(q); // 어떤 질문글의 답변글인지에 정보작업. mybatis에서는 부모의 일련번호를 사용하지만, JPA에서는 다르다.
		a.setCreateDate(LocalDateTime.now());
		this.answerRepository.save(a);
		
		9)
		// 일련번호가 1인 답변글   select * from answer where id = 1 쿼리로 동작하는 것이 아니라 left join 으로 작동된다.
		Optional<Answer> oa = this.answerRepository.findById(1); // left join 로그에서 확인 할것.
		assertTrue(oa.isPresent());
		Answer a = oa.get();
		assertEquals(4, a.getQuestion().getId()); // 질문글의 4번글을 보고 답변글을 저장.
		
		
		// 답변 데이터를 통해 질문 데이터 찾기 vs 질문 데이터를 통해 답변 데이터 찾기
		Optional<Question> oq = this.questionRepository.findById(4);
		assertTrue(oq.isPresent());
		
		// 작업1과 작업2과 테스트환경에서는 하나의 단위로 관리되어야 하므로, @Transactional 사용해야 한다.
		// 지연방식이 기본값이므로 작업1
		Question q = oq.get(); // 질문글참조.
		
		// 질문글에 대한 답변글 참조
		// q라는 질문객체를 조회할 때, 미리 answer리스트를 모두 가져오는 방식을 즉시(Eager)방식이라고 한다.
		//                       필요한 시점에 가져오는 방식을 지연(Lazy)방식이라고 한다.
		// 작업2
		List<Answer> answserList = q.getAnswerList();
		
		assertEquals(2, answserList.size());
		assertEquals("네 자동으로 생성됩니다.", answserList.get(0).getContent());
		
		*/
		// 테스트 데이터 반영안됨.
		for(int i=1; i<=300; i++) {
			String subject = String.format("테스트 데이터입니다:[%03d]", i);
			String content = "내용무";
//			this.questionService.create(subject, content);
			
			Question q = new Question();
			q.setSubject(subject);
			q.setContent(content);
			q.setCreateDate(LocalDateTime.now());
			this.questionRepository.save(q);
		}
		
	}

}
