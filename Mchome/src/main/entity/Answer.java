package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    // 어떤 질문에 대한 답변인지
    @ManyToOne
    @JoinColumn(name = "question_id", nullable = false)
    private Question question;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
