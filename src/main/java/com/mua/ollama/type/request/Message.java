package com.mua.ollama.type.request;

import lombok.Data;

import java.util.List;

@Data
public class Message {
    private String role;
    private String content;
    private List<Object> images;
    private List<ToolCall> toolCalls;
}