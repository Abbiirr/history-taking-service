package com.medaid.historytaking.greeting.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Document(collection = "questions")
@Data
public class Question {

    @Id
    private String id;

    private String text;
    private String type;
    private String category;

    @Field("answers")
    private List<String> answersList;

    private String label;

    @Field("__v")
    private int version;

    // Constructors, getters, and setters

    public Question() {
    }

    public Question(String text, String type, String category, List<String> answersList, String label, int version) {
        this.text = text;
        this.type = type;
        this.category = category;
        this.answersList = answersList;
        this.label = label;
        this.version = version;
    }

    // Getters and Setters
    // ...
}
