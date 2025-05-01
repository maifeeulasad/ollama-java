package com.mua.ollama.type.response;

import com.mua.ollama.type.request.Message;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Data
public class ShowResponse {
    private String license;
    private String modelfile;
    private String parameters;
    private String template;
    private String system;
    private ModelDetails details;
    private List<Message> messages;
    private Date modifiedAt;
    private Map<String, Object> modelInfo;
    private Map<String, Object> projectorInfo;
}