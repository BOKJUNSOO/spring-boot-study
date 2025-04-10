package com.example.demo.question;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

// 질문 등록 페이지에서 사용자로부터 입력받은 값을 검증하는데 필요한 폼 클래스
// Post 방식으로 전달받은 Form 의 id 속성이 자동으로 바인딩
@Getter
@Setter
public class QuestionForm {
    @NotEmpty(message="제목은 필수항목입니다.")
    @Size(max=200)
    private String subject; //HTML subject와 content가 해당 클래스의 멤버변수로 바인딩 된다

    @NotEmpty(message="내용은 필수항목입니다.")
    private String content;
}
