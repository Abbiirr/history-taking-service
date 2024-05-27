package com.medaid.historytaking.greeting;


import com.medaid.historytaking.greeting.domain.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/questions")
public class QuestionController {

    @Autowired
    private QuestionRepository questionRepository;

    @GetMapping
    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    @GetMapping("/{id}")
    public Question getQuestionById(@PathVariable String id) {
        return questionRepository.findById(id).orElse(null);
    }

    @PostMapping
    public Question createQuestion(@RequestBody Question question) {
        return questionRepository.save(question);
    }

    @PutMapping("/{id}")
    public Question updateQuestion(@PathVariable String id, @RequestBody Question questionDetails) {
        Question question = questionRepository.findById(id).orElse(null);
        question.setText(questionDetails.getText());
        question.setType(questionDetails.getType());
        question.setCategory(questionDetails.getCategory());
        question.setAnswersList(questionDetails.getAnswersList());
        question.setLabel(questionDetails.getLabel());
        question.setVersion(questionDetails.getVersion());
        return questionRepository.save(question);
    }

    @DeleteMapping("/{id}")
    public void deleteQuestion(@PathVariable String id) {
        questionRepository.deleteById(id);
    }
}

