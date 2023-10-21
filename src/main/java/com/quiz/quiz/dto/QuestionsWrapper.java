package com.quiz.quiz.dto;

public record QuestionsWrapper

        (String category,
         String title,
         String option1,
         String option2,
         String option3,
         String option4) {
}
