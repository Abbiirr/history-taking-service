package com.medaid.historytaking.greeting.domain;

import lombok.Data;

@Data
public class ConnectedNodeWithWeight {
    private String connectedNode;
    private Double edgeWeight;
}
