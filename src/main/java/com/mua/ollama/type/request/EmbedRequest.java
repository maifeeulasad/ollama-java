package com.mua.ollama.type.request;

import com.mua.ollama.type.Options;
import lombok.Data;

@Data
public class EmbedRequest {
    private String model;
    private Object input; // String or List<String>
    private Boolean truncate;
    private Object keepAlive;

    private Options options;
}