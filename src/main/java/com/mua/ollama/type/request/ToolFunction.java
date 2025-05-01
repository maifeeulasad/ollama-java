package com.mua.ollama.type.request;

import lombok.Data;

@Data
public class ToolFunction {
    private String name;
    private String description;
    private String type;
    private ToolParameters parameters;
}