package com.mua.ollama.type.response;

import com.mua.ollama.type.request.Message;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Data
public class GenerateResponse {
    private String model;
    private Date createdAt;
    private String response;
    private boolean done;
    private String doneReason;
    private List<Integer> context;
    private long totalDuration;
    private long loadDuration;
    private int promptEvalCount;
    private long promptEvalDuration;
    private int evalCount;
    private long evalDuration;
}