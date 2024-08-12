package com.mysite.sbb.answer;

import java.security.Principal;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mysite.sbb.question.Question;
import com.mysite.sbb.question.QuestionService;
import com.mysite.sbb.user.SiteUser;
import com.mysite.sbb.user.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequestMapping("/answer")
@RequiredArgsConstructor
@Controller
public class AnswerController {

	private final QuestionService questionService;
	private final AnswerService answerService;
	private final UserService userService;
	
	
	// 답변하기
	@PreAuthorize("isAuthenticated()")
	@PostMapping("/create/{id}")
	public String createAnswer(Model model, @PathVariable("id") Integer id, 
										@Valid AnswerForm answerForm, BindingResult bindingResult, Principal principal) {
		
		// 질문글의 정보
		Question question = this.questionService.getQuestion(id); // 질문글 일련번호
		// 사용자 정보
		SiteUser siteUser = this.userService.getUser(principal.getName()); // 사용자 ID
		
		if(bindingResult.hasErrors()) {
			model.addAttribute("question", question);
			return "question_detail";
		}
		
		// 답변저장
		this.answerService.create(question, answerForm.getContent(), siteUser);
		
		return String.format("redirect:/question/detail/%s", id);
	}
}
