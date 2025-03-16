package com.QuizzApp.demo.Service;

import com.QuizzApp.demo.Model.Question;
import com.QuizzApp.demo.Model.QuestionWrapper;
import com.QuizzApp.demo.Model.Quiz;
import com.QuizzApp.demo.Model.Response;
import com.QuizzApp.demo.dao.Question_dao;
import com.QuizzApp.demo.dao.QuizDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    QuizDao quizdao;

    @Autowired
    Question_dao qdao;

    public ResponseEntity<String> createQuiz(String difficultyLevel, String title) {
        try {
            List<Question> questions = qdao.findQuestionsByDifficulty(difficultyLevel);

            if (questions.isEmpty()) {
                return new ResponseEntity<>("No questions found for difficulty level: " + difficultyLevel, HttpStatus.NOT_FOUND);
            }

            Quiz quiz = new Quiz();
            quiz.setTitle(title);
            quiz.setQuestions(questions);

            quizdao.save(quiz);
            return new ResponseEntity<>("Quiz created successfully!", HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity<>("Error creating quiz: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
        Optional <Quiz> quiz = quizdao.findById(id);
        List<Question> questionFromDb = quiz.get().getQuestions();
        List<QuestionWrapper> userQuestions = new ArrayList<>();
        for (Question q : questionFromDb)
        {
            QuestionWrapper qw = new QuestionWrapper(q.getId(),q.getQuestion(),q.getOption1(),q.getOption2(),q.getOption3(),q.getOption4());
            userQuestions.add(qw);
        }
        return new ResponseEntity<>(userQuestions,HttpStatus.OK);
    }

    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
        Quiz quiz = quizdao.findById(id).get();
        List<Question> questions = quiz.getQuestions();
        int right = 0;
        int i =0;
        for(Response response : responses)
        {
            if(response.getResponse().equals(questions.get(i).getRightAnswer()))
            {
                right+=1;
            }
            i++;
        }
        return new ResponseEntity<>(right,HttpStatus.OK);
    }
}

