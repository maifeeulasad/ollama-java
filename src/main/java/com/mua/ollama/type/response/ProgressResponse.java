package com.mua.ollama.type.response;

import lombok.Data;

@Data
public class ProgressResponse {
    private String status;
    private String digest;
    private long total;
    private long completed;
}