package com.medaid.historytaking.greeting.domain;

import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import lombok.Data;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.Set;

@Data
@Node("Node")
public class QANode {
    @Id
    private String name;
    private Double weight;

    @Relationship(type = "ASSOCIATED_WITH")
    private Set<QANode> associatedWith;

    @Relationship(type = "CONNECTED_TO")
    GraphQuestion connectedTo;
}
