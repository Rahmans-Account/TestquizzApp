package com.QuizzApp.demo.dao;

import com.QuizzApp.demo.Model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Question_dao extends JpaRepository<Question, Integer> {
   List<Question> findBydifficultyLevel(String difficultyLevel);

   @Query(value = "SELECT * FROM question WHERE difficulty_ldtg evel = 'easy' LIMIT 5", nativeQuery = true)
   List<Question> findQuestionsByDifficulty(@Param("difficultyLevel") String difficultyLevel);



}
