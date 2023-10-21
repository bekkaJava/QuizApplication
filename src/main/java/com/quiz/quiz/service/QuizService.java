package com.quiz.quiz.service;

import com.quiz.quiz.dto.QuestionsWrapper;
import com.quiz.quiz.model.Quiz;
import com.quiz.quiz.userresponse.UserResponse;

import java.util.List;

public interface QuizService {

    Quiz createQuiz(String category, String title, Integer numOfQuestions);
    List<QuestionsWrapper> findQuizQuestions(Long quizId);
    String calculateResult(Long quizId, List<UserResponse> responses);
}
