package com.mua.ollama.type.request;

import lombok.Data;

@Data
public class CopyRequest {
    private String source;
    private String destination;
}