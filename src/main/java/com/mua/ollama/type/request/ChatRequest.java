package com.mua.ollama.type.request;

import com.mua.ollama.type.Options;
import lombok.Data;

import java.util.List;

@Data
public class ChatRequest {
    private String model;
    private List<Message> messages;
    private Boolean stream;
    private Object format;
    private Object keepAlive;
    private List<Tool> tools;

    private Options options;
}