package com.mysite.sbb.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

// 로그인 인증을 담당하는 클래스
@RequiredArgsConstructor
@Service
public class UserSecurityService implements UserDetailsService {

	private final UserRepository userRepository;
	
	// 이 메서드를 재정의하여, 로그인 인증작업을 하도록 제공하고 있다. 그리고, 시큐리티에서 설정작업을 해야 한다.
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Optional<SiteUser> _siteUser = this.userRepository.findByusername(username);
		
		if(_siteUser.isEmpty()) {
			throw new UsernameNotFoundException("사용자를 찾을수 없습니다."); // 예외발생 시키기(예외던지기)
		}
		SiteUser siteUser = _siteUser.get();
		
		// GrantedAuthority : 권한부여를 제공하는 인터페이스
		List<GrantedAuthority> authorities = new ArrayList<>();
		
		// 로그인한 사용자가 관리자 인지 일반인지에 따라서 권한작업 정보를 추가.
		if("admin".equals(username)) {
			authorities.add(new SimpleGrantedAuthority(UserRole.ADMIN.getValue()));
		}else {
			authorities.add(new SimpleGrantedAuthority(UserRole.USER.getValue()));
		}
		
		return new User(siteUser.getUsername(), siteUser.getPassword(), authorities);
	}

}
