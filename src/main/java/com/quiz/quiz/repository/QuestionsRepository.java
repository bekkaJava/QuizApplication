package com.quiz.quiz.repository;

import com.quiz.quiz.model.Questions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QuestionsRepository extends JpaRepository<Questions, Long> {

    List<Questions> findByCategory(String category);

    @Query(value = "SELECT * FROM questions q where q.category=:category ORDER BY RAND() LIMIT :numOfQuestions",
            nativeQuery = true)
    List<Questions> findRandomQuestions(String category, Integer numOfQuestions);
}
