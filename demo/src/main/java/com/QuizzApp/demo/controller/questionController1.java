package com.QuizzApp.demo.controller;

import com.QuizzApp.demo.Model.Question;
import com.QuizzApp.demo.Service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("question")
public class questionController1 {

    @Autowired
    QuestionService qs;

    @GetMapping("questions")
    public ResponseEntity<List<Question>> getAllQuestions()
    {
        return qs.getAllQuestions();

    }

    @GetMapping("level/{difficultyLevel}")

    public ResponseEntity<List<Question>> getQuestionById(@PathVariable String difficultyLevel)
    {
        return qs.findByDifficultyLevel(difficultyLevel);
    }

    @DeleteMapping("deleteQuestion/{id}")  // ✅ Make sure {id} is present
    public ResponseEntity<String> deleteQuestion(@PathVariable int id) {
        qs.deleteQuestion(id);
        return ResponseEntity.ok("Question deleted successfully.");

    }
    @PostMapping("add")
    public ResponseEntity<String> addQuestion (@RequestBody Question question)
    {
        return qs.addQuestions(question);
    }
}