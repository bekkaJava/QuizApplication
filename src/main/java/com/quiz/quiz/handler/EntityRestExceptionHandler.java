package com.quiz.quiz.handler;

import com.quiz.quiz.exception.QuestionNotFoundException;
import com.quiz.quiz.exception.QuizNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@ControllerAdvice
public class EntityRestExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(BAD_REQUEST)
    public ResponseEntity<Map<String, String>> handleValidationException(MethodArgumentNotValidException e) {

        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();

        Map<String, String> errors = new HashMap<>();

        for (FieldError fieldError : fieldErrors) {
            errors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }

        return new ResponseEntity<>(errors, BAD_REQUEST);
    }


    @ExceptionHandler(QuestionNotFoundException.class)
    public ResponseEntity<EntityErrorResponse> questionNotFound(QuestionNotFoundException e) {

        EntityErrorResponse error = new EntityErrorResponse(
                e.getMessage(),
                NOT_FOUND,
                System.currentTimeMillis());

        return new ResponseEntity(error, NOT_FOUND);
    }

    @ExceptionHandler(QuizNotFoundException.class)
    public ResponseEntity<EntityErrorResponse> quizNotFound(QuizNotFoundException e) {

        EntityErrorResponse error = new EntityErrorResponse(
                e.getMessage(),
                NOT_FOUND,
                System.currentTimeMillis());

        return new ResponseEntity(error, NOT_FOUND);
    }
}
