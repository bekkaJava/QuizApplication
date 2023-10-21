package com.quiz.quiz.service;

import com.quiz.quiz.dto.QuestionsDTOMapper;
import com.quiz.quiz.dto.QuestionsWrapper;
import com.quiz.quiz.exception.QuizNotFoundException;
import com.quiz.quiz.model.Questions;
import com.quiz.quiz.model.Quiz;
import com.quiz.quiz.repository.QuestionsRepository;
import com.quiz.quiz.repository.QuizRepository;
import com.quiz.quiz.userresponse.UserResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class QuizServiceImpl implements QuizService {
    private final QuizRepository quizRepository;
    private final QuestionsRepository questionsRepository;
    private final QuestionsDTOMapper questionsDTOMapper;

    @Override
    public Quiz createQuiz(String category, String title, Integer numOfQuestions) {

        List<Questions> questions = questionsRepository.findRandomQuestions(category, numOfQuestions);

        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        quizRepository.save(quiz);

        return quiz;

    }

    @Override
    public List<QuestionsWrapper> findQuizQuestions(Long quizId) {

        Quiz quiz = quizRepository.findById(quizId)
                .orElseThrow(() -> new QuizNotFoundException("Quiz with id %d not found", quizId));

        List<Questions> questions = quiz.getQuestions();

        List<QuestionsWrapper> wrappedQuestions = questions.stream()
                .map(questionsDTOMapper)
                .collect(Collectors.toList());

        return wrappedQuestions;
    }

    @Override
    public String calculateResult(Long quizId, List<UserResponse> responses) {

        Quiz quiz = quizRepository.findById(quizId).
                orElseThrow(() -> new QuizNotFoundException("Quiz with id %d not found", quizId));

        List<Questions> dbQuizQuestions = quiz.getQuestions();

        int result = 0;

        for (int i = 0; i < dbQuizQuestions.size(); i++) {

            if (dbQuizQuestions.get(i).getRightAnswer().equals(responses.get(i).response())) {

                result++;
            }
        }

        return "Your result is " + result + " out of " + dbQuizQuestions.size();

    }


}
