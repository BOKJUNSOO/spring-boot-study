package com.example.demo.answer;

import com.example.demo.question.Question;
import com.example.demo.question.QuestionService;
import com.example.demo.answer.AnswerService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequiredArgsConstructor
@Controller
@RequestMapping("/answer")
public class AnswerController {
    private final QuestionService questionService;
    private final AnswerService answerService;

    @PostMapping("/create/{id}")
    public String creteAnswer(Model model,
                              // PathVariable 어노테이션은 URL에서 id 부분을 파싱해오고, Id 변수에 저장한다.
                              @PathVariable("id") Integer Id,
                              // RequestParam 어노테이션은 답변으로 입력한 내용을 참조한다.
                              @RequestParam(value="content") String content) {
        // question에 맞는 answer을 저장하기 위해
        Question question = this.questionService.getQuestion(Id);
        /*TODO: content를 저장한다.*/ // TODO 는 아직 구현을 하지 않은 부분을 체크하는 용도로 사용한다!
        // question 객체에 answer 을 저장한다. answer 객체는 따로 존재하지 않는다.
        this.answerService.create(question, content);
        return String.format("redirect:/question/detail/%s",Id);
    }
}
