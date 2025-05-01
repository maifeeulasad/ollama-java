package com.mua.ollama.type.response;

import lombok.Data;

import java.util.List;

@Data
public class ModelDetails {
    private String parentModel;
    private String format;
    private String family;
    private List<String> families;
    private String parameterSize;
    private String quantizationLevel;
}