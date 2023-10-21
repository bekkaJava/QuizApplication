package com.quiz.quiz.exception;

public class QuizNotFoundException extends RuntimeException {
    public QuizNotFoundException(String message, Object... args) {
        super(String.format(message,args));
    }
}
