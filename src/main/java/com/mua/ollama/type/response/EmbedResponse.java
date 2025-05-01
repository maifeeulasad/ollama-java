package com.mua.ollama.type.response;

import lombok.Data;

import java.util.List;

@Data
public class EmbedResponse {
    private String model;
    private List<List<Double>> embeddings;
    private long totalDuration;
    private long loadDuration;
    private int promptEvalCount;
}

