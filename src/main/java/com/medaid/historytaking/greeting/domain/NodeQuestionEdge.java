package com.medaid.historytaking.greeting.domain;

import lombok.Data;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

@Data
public class NodeQuestionEdge {
    private String label;
    private String text;
    private String type;
    private String category;
    private String answers__001;
    private String answers__002;
}
