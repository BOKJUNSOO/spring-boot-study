package com.example.demo.entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter // 엔티티를 만들 때에는 Setter 메서드를 사용하지 않기를 권한다.
@Entity // Entity 의 속성은 컬럼 애너테이션을 사용하지 않아도 열로 인식하긴한다.
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 200)
    private String subject; // 제목 칼럼

    @Column(columnDefinition = "TEXT")
    private String content; // 질문 칼럼
    
    // 테이블 에서는 스네이크 케이스 형식으로 변환
    private LocalDateTime createDate;
    
    @OneToMany(mappedBy ="question",cascade = CascadeType.REMOVE)
    private List<Answer> answerList;
}