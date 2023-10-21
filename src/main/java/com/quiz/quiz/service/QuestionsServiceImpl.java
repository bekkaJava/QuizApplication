package com.quiz.quiz.service;

import com.quiz.quiz.dto.QuestionsDTOMapper;
import com.quiz.quiz.dto.QuestionsWrapper;
import com.quiz.quiz.exception.QuestionNotFoundException;
import com.quiz.quiz.model.Questions;
import com.quiz.quiz.repository.QuestionsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class QuestionsServiceImpl implements QuestionsService {
    private final QuestionsRepository questionsRepository;
    private final QuestionsDTOMapper questionsDTOMapper;


    @Override
    public List<QuestionsWrapper> findAllQuestions() {
        return questionsRepository.findAll()
                .stream()
                .map(questionsDTOMapper)
                .collect(Collectors.toList());
    }


    @Override
    public QuestionsWrapper findQuestionById(Long id) {
        return questionsRepository.findById(id)
                .map(questionsDTOMapper)
                .orElseThrow(() -> new QuestionNotFoundException("Question with id %d not found", id));
    }


    @Override
    public void createQuestion(Questions question) {

        questionsRepository.save(question);

    }


    @Override
    public List<QuestionsWrapper> findQuestionsByCategory(String category) {

        return questionsRepository.findByCategory(category)
                .stream()
                .map(questionsDTOMapper)
                .collect(Collectors.toList());
    }
}
