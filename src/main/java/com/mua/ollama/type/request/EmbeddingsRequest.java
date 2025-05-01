package com.mua.ollama.type.request;

import com.mua.ollama.type.Options;
import lombok.Data;

@Data
public class EmbeddingsRequest {
    private String model;
    private String prompt;
    private Object keepAlive;

    private Options options;
}