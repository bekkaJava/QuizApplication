package com.quiz.quiz.controller;

import com.quiz.quiz.dto.QuestionsWrapper;
import com.quiz.quiz.model.Questions;
import com.quiz.quiz.service.QuestionsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/questions")
@RequiredArgsConstructor
public class QuestionsController {
    private final QuestionsService questionsService;

    @GetMapping("/{questionId}")
    public ResponseEntity<QuestionsWrapper> findQuestionById(@PathVariable Long questionId) {

        QuestionsWrapper question = questionsService.findQuestionById(questionId);

        return ResponseEntity.ok(question);

    }

    @GetMapping("/")
    public ResponseEntity<List<QuestionsWrapper>> findAllQuestions() {

        List<QuestionsWrapper> questions = questionsService.findAllQuestions();

        return ResponseEntity.ok(questions);
    }

    @GetMapping("category/{category}")
    public ResponseEntity<List<QuestionsWrapper>> findQuestionsByCategory(@PathVariable String category) {

        List<QuestionsWrapper> questions = questionsService.findQuestionsByCategory(category);

        return ResponseEntity.ok(questions);

    }

    @PostMapping("/")
    public ResponseEntity<Void> createQuestion(@RequestBody Questions question) {

        questionsService.createQuestion(question);

        return ResponseEntity.ok().build();
    }


}
