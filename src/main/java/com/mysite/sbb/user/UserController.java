package com.mysite.sbb.user;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping("/user")
public class UserController {

	private final UserService userService;
	
	@GetMapping("/signup")
	public String signup(UserCreateForm userCreateForm) {
		return "signup_form";
	}
	
	@PostMapping("/signup")
	public String signup(@Valid UserCreateForm userCreateForm, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "signup_form";
		}
		
		if(!userCreateForm.getPassword1().equals(userCreateForm.getPassword2())) {
			bindingResult.rejectValue("password2", "passwordInCorrect", "2개의 패스워드가 일치하지않습니다.");
			return "signup_form";
		}
		
		//회원가입. 중복데이타 검사
		try {
			userService.create(userCreateForm.getUsername(), userCreateForm.getEmail(), userCreateForm.getPassword1());
		}catch(DataIntegrityViolationException e) {
			bindingResult.reject("signupFailed", "이미 등록된 사용자입니다.");
			return "signup_form";
		}catch(Exception e) {
			e.printStackTrace();
			bindingResult.reject("signupFailed", e.getMessage());
		}
		
		return "redirect:/";
	}
	
	// 로그인주소는 설정과 동일하게 작업.
	@GetMapping("/login")
	public String login() {
		return "login_form";
	}
	
	// 로그아웃주소는 작업하지 않는다. 어디서? SecurityCofnig.java 스프링시큐리티에서 설정한다.
	
	
}
