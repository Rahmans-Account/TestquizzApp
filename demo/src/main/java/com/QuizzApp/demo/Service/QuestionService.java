package com.QuizzApp.demo.Service;

import com.QuizzApp.demo.Model.Question;
import com.QuizzApp.demo.dao.Question_dao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    @Autowired
    Question_dao qdao;

    public ResponseEntity<List<Question>> getAllQuestions()
    {
        try {
            return new ResponseEntity<>(qdao.findAll(), HttpStatus.OK);
        }
        catch (Exception e){
            e.printStackTrace();
        }return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);

    }
    public ResponseEntity<List<Question>> findByDifficultyLevel(String  difficultyLevel){

        try {
            return new ResponseEntity<>(qdao.findBydifficultyLevel(difficultyLevel), HttpStatus.OK);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);


    }
    public ResponseEntity<String> addQuestions(Question question)
    {
        try {
            qdao.save(question);
            return new ResponseEntity<>("Sucess",HttpStatus.OK);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);


    }
    public String deleteQuestion(int id)
    {

        qdao.deleteById(id);
        return null;
    }

}