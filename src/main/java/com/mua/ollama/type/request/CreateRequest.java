package com.mua.ollama.type.request;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class CreateRequest {
    private String model;
    private String from;
    private Boolean stream;
    private String quantize;
    private String template;
    private Object license;
    private String system;
    private Map<String, Object> parameters;
    private List<Message> messages;
    private Map<String, String> adapters;
}
