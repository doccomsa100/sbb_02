package com.mysite.sbb.answer;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.mysite.sbb.DataNotFoundException;
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
	
	// 질문글 삭제
	public void delete(Answer answer) {
		this.answerRepository.delete(answer);
	}
	
	// 답변글 조회. 
	public Answer getAnswer(Integer id) {
		Optional<Answer> answer = this.answerRepository.findById(id);
		if(answer.isPresent()) {
			return answer.get();
		}else {
			throw new DataNotFoundException("answer not found");
		}
		
	}
}
