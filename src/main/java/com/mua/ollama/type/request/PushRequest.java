package com.mua.ollama.type.request;

import lombok.Data;

@Data
public class PushRequest {
    private String model;
    private Boolean insecure;
    private Boolean stream;
}