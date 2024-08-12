package com.mysite.sbb.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

// 클라이언트 : 자바스트립트 유효성
// 서버(백엔드) : Validation 기능
@Getter
@Setter
public class UserCreateForm {

	@Size(min = 3, max = 25)
	@NotEmpty(message = "사용자ID는 필수항목입니다.")
	private String username;
	
	@NotEmpty(message = "비밀번호는 필수항목입니다.")
	private String password1;
	
	@NotEmpty(message = "비밀번호 확인은 필수항목입니다.")
	private String password2;
	
	@NotEmpty(message = "전자우편은 필수항목입니다.")
	@Email
	private String email;
}
