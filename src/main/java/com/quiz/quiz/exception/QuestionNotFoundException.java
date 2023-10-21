package com.quiz.quiz.exception;

public class QuestionNotFoundException extends RuntimeException {

    public QuestionNotFoundException(String message, Object... args) {
        super(String.format(message,args));
    }
}
