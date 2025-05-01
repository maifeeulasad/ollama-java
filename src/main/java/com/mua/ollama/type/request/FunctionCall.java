package com.mua.ollama.type.request;

import lombok.Data;

import java.util.Map;

@Data
public class FunctionCall {
    private String name;
    private Map<String, Object> arguments;
}
