package com.mua.ollama.type.response;

import lombok.Data;

import java.util.List;

@Data
public class EmbeddingsResponse {
    private List<Double> embedding;
}