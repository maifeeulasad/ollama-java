package com.mua.ollama.type.response;

import lombok.Data;

import java.util.Date;

@Data
public class ModelResponse {
    private String name;
    private Date modifiedAt;
    private String model;
    private long size;
    private String digest;
    private ModelDetails details;
    private Date expiresAt;
    private long sizeVram;
}