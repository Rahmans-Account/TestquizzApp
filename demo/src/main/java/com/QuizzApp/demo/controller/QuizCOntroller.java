package com.QuizzApp.demo.controller;

import com.QuizzApp.demo.Model.Question;
import com.QuizzApp.demo.Model.QuestionWrapper;
import com.QuizzApp.demo.Model.Response;
import com.QuizzApp.demo.Service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")

public class QuizCOntroller {

    @Autowired
    QuizService quizservice;
    @PostMapping("create")
    public ResponseEntity<String> createQuiz(@RequestParam String difficultyLevel,@RequestParam String title){
        return quizservice.createQuiz(difficultyLevel,title);
    }
    @GetMapping("get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable Integer id)
    {
        return quizservice.getQuizQuestions(id);
    }
    @PostMapping("submit/{id}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable Integer id , @RequestBody List<Response> responses)
    {
        return quizservice.calculateResult(id,responses);
    }

}
