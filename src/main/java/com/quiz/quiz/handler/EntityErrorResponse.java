package com.quiz.quiz.handler;

import org.springframework.http.HttpStatus;

public record EntityErrorResponse(String message,
                                  HttpStatus httpStatus,
                                  long timeStamp) {
}
