package com.quiz.quiz.dto;

import com.quiz.quiz.model.Questions;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class QuestionsDTOMapper implements Function<Questions, QuestionsWrapper> {

    @Override
    public QuestionsWrapper apply(Questions questions) {

        return new QuestionsWrapper(questions.getCategory(),
                questions.getTitle(),
                questions.getOption1(),
                questions.getOption2(),
                questions.getOption3(),
                questions.getOption4());
    }
}
