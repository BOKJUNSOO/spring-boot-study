package com.example.demo.question;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import lombok.RequiredArgsConstructor;


// question/list URL 매핑을 하기 위한 컨트롤러
@RequestMapping("/question") // 해당 class 에서 맵핑된 URL 은 모두 `question`을 포함한다
@RequiredArgsConstructor
@Controller
public class QuestionController {
    // RequiredArgsConstructor 는 롬복이 제공하는 애너테이션
    // final 이 붙은 속성을 포함하는 생성자를 자동으로 만들어준다.
    // 따라서 QuestionController 가 생성할때 자동으로 QuestionRepository 객체가 주입
    // private final QuestionRepository questionRepository;

    private final QuestionService questionService;
    @GetMapping(value="/list")
    public String list(Model model) {
        List<Question> questionList = this.questionService.getList();
        // repository 를 이용하여 데이터를 가져온다. 해당 부분을 Service 가 수행
        // List<Question> questionList = this.questionRepository.findAll();

        // Model 객체에 "questionList" 라는 이름으로 questionList 객체를 저장
        // 이는 자바 Class 와 template 에 html 과 연결고리 역할을 한다.
        // model 객체를 참조하여 템플릿에서 그 값을 사용할 수 있다.
        // `ViewResolver`이 그 역할을 수행한다
        model.addAttribute("questionList",questionList);

        // Thymeleaf 템플릿 엔진은
        // 컨트롤러의 리턴값을 파싱하여 해당하는 html 파일을 리턴한다
        return "question_list";
    }

    @GetMapping(value="/detail/{id}")
    public String detail(Model model, @PathVariable("id") Integer Id) {
        // 서비스를 이용하여 question 객체에 id 저장
        Question question = this.questionService.getQuestion(Id);
        model.addAttribute("question",question);
        return "question_detail";
    }

    // 같은 클래스에서 overloading
    // URL 요청시 GET 방식으로 HTML 응답
    @GetMapping("/create")
    public String questionCreate() {
        return "question_form";
    }

    // 다른 인자로 오버로딩
    // PostMapping 을 통한 사용자가 서버에 요청 (HTML FORM 태그에 POST 방식 명시)
    @PostMapping("/create")
    public String quesitonCreate(// RequestParam 을 이용하여 입력받은 값을 자바객체로 저장(html name 태그)
                                 @RequestParam(value="subject") String subject,
                                 @RequestParam(value="content") String content) {
        this.questionService.create(subject,content);
        return "redirect:/question/list";
    }
}
