package com.mysite.sbb;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class MainController {

	@GetMapping("/sbb")
	@ResponseBody
	public String index() {
		log.info("index");
		return "안녕하세요 스프링부트 JPA 학습을 진행합니다.";
	}
	
	@GetMapping("/")
	public String root() {
		return "redirect:/question/list";
	}
}
