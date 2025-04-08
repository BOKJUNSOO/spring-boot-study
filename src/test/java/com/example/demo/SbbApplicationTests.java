package com.example.demo;

// 테스트의 통과여부를 결정짖는 가장 중요한 함수
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;
import java.util.List;

import com.example.demo.answer.Answer;
import com.example.demo.answer.AnswerRepository;
import com.example.demo.question.Question;
import com.example.demo.question.QuestionRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

// spring boot Test 애너테이션은 해당 클래스가 테스트 클래스임을 의미한다
@SpringBootTest
class SbbApplicationTests {
	// 스프링의 의존성 주입 기능을 사용할 수 있다
	// setter 메서드를 사용할 수 있으나 순환 참조 문제이유로 해당 어노테이션을 권장하진 않는다.
	// 하지만 테스트 코드에서는 생성자를 통한 객체 주입을 지원하지 않으므로 테스트 코드 작성 시에만 사용한다
	@Autowired
	private QuestionRepository questionRepository;
	
	// AutoWired 어노테이션을 통해 AnswerRepository 의 객체를 주입
	@Autowired
	private AnswerRepository answerRepository;

	// Test 어노테이션은 해당 메서드가 테스트 메서드임을 나타낸다.
	@Test
	@DisplayName("Repository DB 참조 test")
	void testJpa(){
		//의존성 주입 테스트
		/*Question q1 = new Question();
		q1.setSubject("sbb가 무엇인가요?");
		q1.setContent("sbb에 대해서 알고싶습니다.");
		q1.setCreateDate(LocalDateTime.now());
		this.questionRepository.save(q1);

		Question q2 = new Question();
		q2.setSubject("스프링부트 모델 질문입니다.");
		q2.setContent("id는 자동으로 생성되나요?");
		q2.setCreateDate(LocalDateTime.now());
		this.questionRepository.save(q2);*/
		
		// 리포지터리의 findAll 메서드 
		// 테이블에 저장된 모든 데이터를 조회한다
		// assertEquals 메서드는 (예상한 결과, 실제 가져온 결과) 둘을 비교하기 위해 사용한다.
		/*List<Question> all = this.questionRepository.findAll();
		Question q = all.get(0);
		assertEquals("sbb가 무엇인가요?",q.getSubject());*/

		// 리포지터리의 findById 메서드
		// Id 값으로 데이터를 조회한다. 이때 findById의 리턴값은 Optional 이다.
		/*Optional<Question> oq = this.questionRepository.findById(1);
		// isPresent 는 값의 존재 유무를 확인한다.
		if (oq.isPresent()) {
			// 값이 존재한다면 get 메서드를 통해 Question 객체의 값을 얻는다.
			Question q = oq.get();
			assertEquals("sbb가 무엇인가요?", q.getSubject());*/
		
		// 리포지터리의 findBySubject 메서드
		// 단순히 Interface 에 JPA가 네이밍 규칙을 기반으로 자동으로 구현
		// 인터페이스를 통해 DB에 값을 참조한다.
		// Question q = this.questionRepository.findBySubject("sbb가 무엇인가요?");
		// 해당 제목의 Id가 "1"이 아닌 다른 Id도 존재한다면 테스트 실패로 출력!
		// assertEquals(1, q.getId());

		// findBy속성and속성
		/*Question q = this.questionRepository.findBySubjectAndContent(
				"sbb가 무엇인가요?", "sbb에 대해서 알고싶습니다.");
		assertEquals(1,q.getId());
		 */

		// SQL like 키워드
		/*
		List<Question> qList = this.questionRepository.findBySubjectLike("sbb%");
		Question q = qList.get(0);
		assertEquals("sbb가 무엇인가요?", q.getSubject());
		 */

	}
	/*
	@Test
	@DisplayName("DB 초기화 및 할당")
	void testInitJpa() {
		Optional<Question> oq = this.questionRepository.findById(1);
		assertTrue(oq.isPresent()); // 내부가 true 라면 테스트 통과
		Question q = oq.get();
		q.setSubject("수정된 제목"); // 로그에서 update 확인 가능하다.
		this.questionRepository.save(q);
	}
	@Test
	@DisplayName("DB 값 삭제")
	void testDelete() {
		assertEquals(2,this.questionRepository.count());
		Optional<Question> oq = this.questionRepository.findById(1);
		assertTrue(oq.isPresent());
		Question q = oq.get();
		this.questionRepository.delete(q);
		assertEquals(1,this.questionRepository.count());
	}

	@Test
	@DisplayName("답변 데이터 저장하기")
	void testSave() {
		Optional<Question> oq = this.questionRepository.findById(2);
		assertTrue(oq.isPresent());
		Question q = oq.get();

		// 답변 데이터 저장하기
		// answer은 왜 객체 자동생성 안되지?
		Answer a = new Answer();
		a.setContent("네 자동으로 생성됩니다.");
		a.setQuestion(q);
		a.setCreateDate(LocalDateTime.now());
		this.answerRepository.save(a);
	}
	@Test
	@DisplayName("답변 데이터 조회하기")
	void testJpa2() {
		Optional<Answer> oa = this.answerRepository.findById(1);
		assertTrue(oa.isPresent());
		Answer a = oa.get();
		assertEquals(2,a.getQuestion().getId());
	}*/

	@Transactional
	@Test
	@DisplayName("Transactinal 확인")
	void testJpaTra() {
		Optional<Question> oq = this.questionRepository.findById(2); // DB 세션 종료코드
		assertTrue(oq.isPresent());
		Question q = oq.get();

		List<Answer> answerList = q.getAnswerList(); // Transactinal 어노테이션으로 실행 가능

		assertEquals(1, answerList.size());
		assertEquals("네 자동으로 생성됩니다.", answerList.get(0).getContent());
	}

}
