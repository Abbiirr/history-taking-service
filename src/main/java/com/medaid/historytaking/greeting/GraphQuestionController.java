package com.medaid.historytaking.greeting;

import com.medaid.historytaking.greeting.domain.GraphQuestion;
import com.medaid.historytaking.greeting.domain.Question;
import com.medaid.historytaking.reqres.InitialQuestionRequest;
import com.medaid.historytaking.reqres.NextQuestionRequest;
import com.medaid.historytaking.reqres.QuestionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/graph/questions")
@RequiredArgsConstructor
public class GraphQuestionController {

    @Autowired
    private GraphQuestionRepository questionRepository;

    @Autowired
    private QuestionService questionService;


    @GetMapping
    public List<GraphQuestion> getAllQuestions() {
        return questionRepository.findAll();
    }

    @PostMapping("/initial")
    public QuestionResponse takeInitialNode(@RequestBody InitialQuestionRequest initialQuestionRequest) {
        return questionService.addChiefComplaint(initialQuestionRequest.getNodeName());
    }

    @PostMapping("/next")
    public QuestionResponse getNextQuestion(@RequestBody NextQuestionRequest nextQuestionRequest) {
        return questionService.getNextQuestion(nextQuestionRequest.getQuestion(), nextQuestionRequest.getAnswer());
    }
}
