package com.quiz.quiz.dto;


import lombok.Getter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
public class QuizRequest {

    @NotBlank(message = "Category field is required")
    private String category;

    @NotBlank(message = "Title field is required")
    private String title;

    @NotNull(message = "Number of questions is required")
    @Min(value = 5, message = "The number of questions must be at least 5")
    @Max(value = 30, message = "The number of questions cannot exceed 30")
    private Integer numOfQuestions;

}
