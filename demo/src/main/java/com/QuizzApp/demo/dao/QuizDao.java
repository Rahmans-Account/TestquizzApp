package com.QuizzApp.demo.dao;

import com.QuizzApp.demo.Model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizDao extends JpaRepository<Quiz,Integer> {

}
