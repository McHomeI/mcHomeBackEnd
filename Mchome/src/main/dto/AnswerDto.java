package com.example.demo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnswerDto {

    @Schema(description = "답변을 달 질문의 ID")
    private Long questionId;

    @Schema(description = "답변 내용")
    private String content;

    @Schema(description = "관리자 키 (고정)")
    private String adminKey;
}
