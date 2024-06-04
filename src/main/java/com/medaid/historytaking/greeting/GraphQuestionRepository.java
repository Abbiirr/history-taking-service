package com.medaid.historytaking.greeting;

import com.medaid.historytaking.greeting.domain.GraphQuestion;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GraphQuestionRepository extends Neo4jRepository<GraphQuestion, String> {
     @Query("MATCH (:Node {name: $nodeName})-[:ASSOCIATED_WITH]->(question:Question) " +
           "RETURN question.label AS label, question.text AS text, " +
           "question.type AS type, question.category AS category")
     List<GraphQuestion> findQuestionByNodeName(@Param("nodeName") String nodeName);
}