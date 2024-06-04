package com.medaid.historytaking.greeting;

import com.medaid.historytaking.greeting.domain.ConnectedNodeWithWeight;
import com.medaid.historytaking.greeting.domain.GraphQuestion;
import com.medaid.historytaking.greeting.domain.QANode;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QANodeRepository extends Neo4jRepository<QANode, String> {
    @Query("MATCH (original:Node)-[r:CONNECTED_TO]->(connected)\n" +
            "WHERE original.name IN $names  // Replace 'node2' and 'node3' with the actual names of the nodes in your list\n" +
            "RETURN original.name AS originalNode, connected.name AS connectedNode, toFloat(r.weight) AS edgeWeight\n" +
            "ORDER BY edgeWeight ASC\n" +
            "LIMIT 208\n\n")
    List<ConnectedNodeWithWeight> findNodesWithLeastWeightedEdge(@Param("names") List<String> names);


    @Query("MATCH (:Node {name: $node})-[r]-()-[r2]-(n) WHERE NOT n.name = $node RETURN n ORDER BY r.weight ASC LIMIT 1")
    QANode findNodeWithLeastWeightedEdge(@Param("node") String node);


    @Query("MATCH (n:QANode)-[:CONNECTED_TO]->(g:GraphQuestion) WHERE g = $graphQuestion RETURN n")
    QANode findByGraphQuestion(GraphQuestion graphQuestion);

    QANode findByName(String name);
}
