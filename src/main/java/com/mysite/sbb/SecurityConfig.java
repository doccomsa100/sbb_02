package com.mysite.sbb;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration // 스프링의 환경설정파일을 의미.
@EnableMethodSecurity(prePostEnabled = true)  // @PreAuthorize("isAuthenticated()") 인증된 사용자만 호출이 가능한 컨트롤러 매핑주소 앞에 선언해야 한다.
@EnableWebSecurity // 모든 URL요청이 스프링 시큐리티의 제어를 받게한다.
public class SecurityConfig {

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
			.authorizeHttpRequests((authorizeHttpRequests) -> authorizeHttpRequests
				.requestMatchers(new AntPathRequestMatcher("/**")).permitAll()) // 모든 URL요청을 허용해서, 시큐리티 로그인주소 작동이 안됨
			.csrf((csrf) -> csrf
					.ignoringRequestMatchers(new AntPathRequestMatcher("/h2-console/**")))
			.headers((headers) -> headers
	                .addHeaderWriter(new XFrameOptionsHeaderWriter(
	                    XFrameOptionsHeaderWriter.XFrameOptionsMode.SAMEORIGIN)))
			.formLogin((formLogin) -> formLogin
					.loginPage("/user/login")
					.defaultSuccessUrl("/"))
			
			// 매핑주소 로그아웃주소 /user/logout 를 컨트롤러에서 작업하지 않는다. 여기 설정으로 작업한다.
			.logout((logout) -> logout
						.logoutRequestMatcher(new AntPathRequestMatcher("/user/logout"))
						.logoutSuccessUrl("/")
						.invalidateHttpSession(true))
				
			;
		return http.build();
	}
	
	// 로그인 인증시 사용하는 bean
	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}
	
	
	// 리턴값을 PasswordEncoder 인터페이스로 하는 이유는? 
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(); // 암호화 클래스 변경될 경우를 생각
	}
}
