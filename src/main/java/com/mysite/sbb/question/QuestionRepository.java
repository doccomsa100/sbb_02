package com.mysite.sbb.question;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

// JPA 리포지터리 : 테이블에 대하여 삽입,삭제, 수정, 조회 작업을 가능하게 해주는 인터페이스
// JpaRepository 인터페이스 : CRUD 작업을 처리하는 메서드들이 내장되어 있다.
// mybatis 로 비교하면, QuestionMapper.java, QuestionMapper.xml 2가지 파일로 관점을 바라본다.
public interface QuestionRepository extends JpaRepository<Question, Integer> {

	
	// 최소한 기능적 메서드가 미리 정의되어 있다. CTRL + SpaceBar
	
	
	//추상메서드명 네이밍규칙이 존재한다.
	
	// findBy + 필드명의 첫문자를 대문자로
	Question findBySubject(String subject);
	Question findBySubjectAndContent(String subject, String content);
	
	Page<Question> findAll(Pageable pageable);
}
