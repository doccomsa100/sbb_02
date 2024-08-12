package com.mysite.sbb.question;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuestionForm {

	// 널 또는 빈문자열 허용안함.
	@NotEmpty(message = "제목은 필수항목입니다.")
	@Size(max = 200) // 최대길이 200바이트를 넘으면 안된다.
	private String subject;
	
	@NotEmpty(message = "내용은 필수항목입니다.")
	private String content;
}
