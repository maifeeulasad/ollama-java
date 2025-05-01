package com.mua.ollama.type.request;

import lombok.Data;

@Data
public class Tool {
    private String type;
    private ToolFunction function;
}