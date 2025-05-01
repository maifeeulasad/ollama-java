package com.mua.ollama.type.request;

import lombok.Data;

@Data
public class ToolCall {
    private FunctionCall function;
}