package com.mua.ollama.type.request;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class ToolParameters {
    private String type;
    private Object defs;
    private Object items;
    private List<String> required;
    private Map<String, ToolProperty> properties;
}
