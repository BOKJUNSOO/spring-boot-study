package com.example.demo.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "TEXT")
    private String content;

    private LocalDateTime createDate; 
    
    // 질문 하나에 여러 답변이 가능하므로 다대:일 의 관계
    @ManyToOne //question.getQuestion().getSubject() 를사용하기 위함
    private Question question;  // 즉 질문 엔티티와 연결된 속성임을 표시
}
