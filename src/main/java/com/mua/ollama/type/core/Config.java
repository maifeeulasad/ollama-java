package com.mua.ollama.type.core;

import lombok.Data;
import java.util.Map;
import java.util.Optional;

@Data
public class Config {
    private String host;
    private Optional<Object> fetch; // todo: write a custom HTTP client
    private Boolean proxy;
    private Map<String, String> headers;
}
