package com.mua.ollama.type.response;

import lombok.Data;

import java.util.List;

@Data
public class ListResponse {
    private List<ModelResponse> models;
}
