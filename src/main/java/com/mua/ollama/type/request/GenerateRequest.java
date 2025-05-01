package com.mua.ollama.type.request;

import com.mua.ollama.type.Options;
import lombok.Data;

import java.util.List;

@Data
public class GenerateRequest {
    private String model;
    private String prompt;
    private String suffix;
    private String system;
    private String template;
    private List<Integer> context;
    private Boolean stream;
    private Boolean raw;
    private Object format; // can be String or Object
    private List<Object> images; // use byte[] or String depending on implementation
    private Object keepAlive; // String or Number

    private Options options;
}
