package com.medaid.historytaking.greeting;

import com.medaid.historytaking.greeting.domain.GraphQuestion;
import com.medaid.historytaking.greeting.domain.ConnectedNodeWithWeight;
import com.medaid.historytaking.greeting.domain.QANode;
import com.medaid.historytaking.reqres.QuestionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private HashMap<String, String> patientQuestionAnswerMap = new HashMap<>();
    private List<String> nodesTraversed = new ArrayList<>();
    @Autowired
    private QANodeRepository qaNodeRepository;
    @Autowired
    private GraphQuestionRepository graphQuestionRepository;


    public QuestionResponse getNextQuestion(String question, String answer) {
        updatePatientHistory(question, answer);
        QANode nextNode = getNextNode();
        nodesTraversed.add(nextNode.getName());
        GraphQuestion nextQuestion = nextNode.getConnectedTo();
        QuestionResponse nextQuestionResponse = mapToQuestionResponse(nextQuestion);
        return nextQuestionResponse;
    }

    public void updatePatientHistory(String question, String answer) {
        patientQuestionAnswerMap.put(question, answer);
//        nodesTraversed.add(qaNodeRepository.findByGraphQuestion(question));
    }

    public QuestionResponse addChiefComplaint(String nodeName) {
        QANode initialNode = qaNodeRepository.findByName(nodeName);
        nodesTraversed.add(initialNode.getName());

        QANode nextNode = getNextNode();
        GraphQuestion nextQuestion = graphQuestionRepository.findQuestionByNodeName(nextNode.getName()).get(0);
        QuestionResponse nextQuestionResponse = mapToQuestionResponse(nextQuestion);

        return nextQuestionResponse;
    }

    private QuestionResponse mapToQuestionResponse(GraphQuestion graphQuestion) {
        return new QuestionResponse().builder()
                .text(graphQuestion.getText())
                .label(graphQuestion.getLabel())
                .type(graphQuestion.getType())
                .answers__001(graphQuestion.getAnswers__001())
                .answers__002(graphQuestion.getAnswers__002())
                .category(graphQuestion.getCategory())
                .build();
    }

    private QANode getNextNode() {
        List<ConnectedNodeWithWeight> connectedNodes = qaNodeRepository.findNodesWithLeastWeightedEdge(nodesTraversed);

        String  leastWeightedNodeNotInList = null;
        Double minWeight = Double.MAX_VALUE; // Initialize with a high value

        for (ConnectedNodeWithWeight connectedNodeWithWeight : connectedNodes) {
            String  connectedNode = connectedNodeWithWeight.getConnectedNode();
            Double weight = connectedNodeWithWeight.getEdgeWeight();

            if (!nodesTraversed.contains(connectedNode) && weight < minWeight) {
                minWeight = weight;
                leastWeightedNodeNotInList = connectedNode;
            }
        }
        return qaNodeRepository.findByName(leastWeightedNodeNotInList);

    }

}
