package com.mysite.sbb.answer;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.mysite.sbb.question.Question;
import com.mysite.sbb.user.SiteUser;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AnswerService {

	private final AnswerRepository answerRepository;
	
	//질문글에 대한 답변저장
	public void create(Question question, String content, SiteUser author) {
		Answer answer = new Answer();
		answer.setContent(content);
		answer.setCreateDate(LocalDateTime.now());
		
		// 질문글에 대한 정보
		answer.setQuestion(question);
		
		// 사용자에 대한 정보
		answer.setAuthor(author);
		
		this.answerRepository.save(answer);
	}
}
