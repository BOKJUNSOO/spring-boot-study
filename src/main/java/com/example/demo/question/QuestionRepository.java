// 리포지터리는 테이블에 접근하고, 데이터를 관리하는 메서드를 제공한다.
package com.example.demo.question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
// JpaRepository 인터페이스를 상속하여 사용한다.
// JPA 가 제공하는 인터페이스 중 하나로 CRUD 작업을 처리하는 메서드들을 이미 내장하고 있어 데이터 관리 작업을 편리하게 할 수 있다.
public interface QuestionRepository extends JpaRepository<Question, Integer>{
    // 네이밍 규칙을 통해 JPA가 자동으로 인터페이스를 구현해준다.
    // Question 엔터티의 SUBJECT 속성을 가진 컬럼을 조회한다
    Question findBySubject(String subject);
    // 인자의 위치도 중요하다 속성과 속성 사이에는 `SQL` 연산자에 해당하는 키워드가 위치한다!
    Question findBySubjectAndContent(String subject, String content);

    // findBySubjectLike 메서드
    List<Question> findBySubjectLike(String subject);
    Page<Question> findAll(Pageable pageable);
}
