package com.quiz.quiz.service;

import com.quiz.quiz.dto.QuestionsWrapper;
import com.quiz.quiz.model.Questions;

import java.util.List;

public interface QuestionsService {

    List<QuestionsWrapper> findAllQuestions();
    QuestionsWrapper findQuestionById(Long id);
    void createQuestion(Questions question);
    List<QuestionsWrapper> findQuestionsByCategory(String category);
}
