package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

// 컨트롤러에 등록한다. 
// 클라이언트의 페이지 요청이 발생하면 컨트롤러에 등록된 URL 매핑을 찾는다
// URL 매핑을 찾으면 연결된 메서드를 실행한다
@Controller
public class MainController {
	@GetMapping("/sbb") // 해당 도메인으로 요청시 아래으 함수를 실행한다.
	@ResponseBody // URL 요청에 문자열로 body 에 리턴
	public String index() {
		return "안녕하세요 sbb에 오신 것을 환영합니다.";
	}
}
