package com.example.demo.question;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import com.example.demo.DataNotFoundException;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

// 서비스의 필요성
    // A,B 컨트롤러에서 리포지토리 구현체의 a,b,c 메서드를 수행한다 할때
    // A,B 컨트롤러에 해당부분을 구현한다면 중복되는 코드가 발생된다.
    // 이런 a,b,c 메서드를 호출하는 기능을 서비스로 만들고 컨트롤러에서 서비스를 호출

    // 엔티티 객체를 DTO 객체로 변환할 수 있다.

// 리포지터리를 직접 사용하지 않고 컨트롤러 -> 서비스 -> 리포지터리 순서로 접근하도록 구현
@RequiredArgsConstructor
@Service
public class QuestionService {

    // RequiredArgsConstructor 는 롬복이 제공하는 애너테이션
    // final 이 붙은 속성을 포함하는 생성자를 자동으로 만들어준다.
    // 따라서 QuestionService 가 인스턴스화 될 때 자동으로 QuestionRepository 객체가 주입
    private final QuestionRepository questionRepository;
    
    public List<Question> getList() {
        return this.questionRepository.findAll();
    }

    public Question getQuestion(Integer Id) {

        // Optional 객체 이므로 `if~else` 문을 통해 해당 데이터 존재 여부를 검사한다.
        Optional<Question> question = this.questionRepository.findById(Id);
        if (question.isPresent()) {
            return question.get();
        } else {
            throw new DataNotFoundException("question not found");
        }
    }
    public void create(String subject, String content) {
        Question q = new Question();
        q.setSubject(subject);
        q.setContent(content);
        q.setCreateDate(LocalDateTime.now());
        this.questionRepository.save(q);
    }
}
