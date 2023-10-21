package com.quiz.quiz.controller;

import com.quiz.quiz.dto.QuestionsWrapper;
import com.quiz.quiz.dto.QuizRequest;
import com.quiz.quiz.model.Quiz;
import com.quiz.quiz.service.QuizService;
import com.quiz.quiz.userresponse.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v2/quiz")
public class QuizController {

    private final QuizService quizService;


    @PostMapping("/create")
    public ResponseEntity<Quiz> createQuiz2(@RequestBody @Valid QuizRequest quizRequest) {

        Quiz quiz = quizService.createQuiz(
                quizRequest.getCategory(),
                quizRequest.getTitle(),
                quizRequest.getNumOfQuestions());

        return ResponseEntity.ok(quiz);
    }

    @GetMapping("/{quizId}")
    public ResponseEntity<List<QuestionsWrapper>> findQuizQuestions(@PathVariable Long quizId) {

        List<QuestionsWrapper> questions = quizService.findQuizQuestions(quizId);

        return ResponseEntity.ok(questions);

    }

    @PostMapping("submit/{quizId}")

    public ResponseEntity<String> submitQuiz(@PathVariable Long quizId,
                                             @RequestBody List<UserResponse> responses) {

        String result = quizService.calculateResult(quizId, responses);

        return ResponseEntity.ok(result);
    }
}
