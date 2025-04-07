package com.example.demo.test.spiring;


import lombok.Getter;


//@Setter
//@RequiredArgsConstructor
@Getter
public class HelloLombok {
	private final String hello; // final 이 없다면 RequiredArgsConstructor 를 사용한 생성자에 포함되지 않는다.
	private final int lombok; // final keyword 로 인한 셋터 메서드의 불필요
	
	public HelloLombok(String hello, int lombok) {
		this.hello = hello;
		this.lombok = lombok;
	}
	public static void main(String[] args) {
		HelloLombok helloLombok = new HelloLombok("hi!",5);
		System.out.println(helloLombok.getHello());
		System.out.println(helloLombok.getLombok());
	}
}
