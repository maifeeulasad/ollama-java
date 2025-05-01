package com.mua.ollama.type.request;

import lombok.Data;

import java.util.List;

@Data
public class ToolProperty {
    private Object type;
    private Object items;
    private String description;
    private List<Object> enumValues;
}
