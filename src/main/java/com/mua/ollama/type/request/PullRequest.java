package com.mua.ollama.type.request;

import lombok.Data;

@Data
public class PullRequest {
    private String model;
    private Boolean insecure;
    private Boolean stream;
}