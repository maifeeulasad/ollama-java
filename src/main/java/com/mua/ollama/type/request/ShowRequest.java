package com.mua.ollama.type.request;

import com.mua.ollama.type.Options;
import lombok.Data;

@Data
public class ShowRequest {
    private String model;
    private String system;
    private String template;
    private Options options;
}
