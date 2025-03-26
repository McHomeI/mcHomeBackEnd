package com.example.demo.controller;

import com.example.demo.dto.AnswerDto;
import com.example.demo.entity.Answer;
import com.example.demo.entity.Question;
import com.example.demo.repository.AnswerRepository;
import com.example.demo.repository.QuestionRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Answer API", description = "답변 작성 및 조회 API")
@RestController
@RequestMapping("/answers")
@RequiredArgsConstructor
public class AnswerController {

    // 관리자 키 (고정 상수)
    private static final String ADMIN_KEY = "MY_SECRET_KEY";

    private final AnswerRepository answerRepository;
    private final QuestionRepository questionRepository;

    @Operation(summary = "답변 작성", description = "관리자 키 검증 후, 해당 질문에 답변을 작성한다.")
    @PostMapping
    public ResponseEntity<?> createAnswer(@RequestBody AnswerDto answerDto) {
        // 관리자 키 검증
        if (!ADMIN_KEY.equals(answerDto.getAdminKey())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body("관리자 키가 올바르지 않습니다.");
        }

        // questionId로 질문 찾기
        Question question = questionRepository.findById(answerDto.getQuestionId())
                .orElse(null);

        if (question == null) {
            return ResponseEntity.badRequest()
                    .body("해당 질문을 찾을 수 없습니다.");
        }

        // 답변 저장
        Answer answer = new Answer();
        answer.setContent(answerDto.getContent());
        answer.setQuestion(question);
        Answer saved = answerRepository.save(answer);

        return ResponseEntity.ok(saved);
    }

    @Operation(summary = "답변 전체 조회", description = "등록된 모든 답변을 조회한다.")
    @GetMapping
    public ResponseEntity<?> getAllAnswers() {
        return ResponseEntity.ok(answerRepository.findAll());
    }
}
