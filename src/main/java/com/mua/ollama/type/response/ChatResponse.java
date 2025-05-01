package com.mua.ollama.type.response;


import com.mua.ollama.type.request.Message;
import lombok.Data;

import java.util.Date;

@Data
public class ChatResponse {
    private String model;
    private Date createdAt;
    private Message message;
    private boolean done;
    private String doneReason;
    private long totalDuration;
    private long loadDuration;
    private int promptEvalCount;
    private long promptEvalDuration;
    private int evalCount;
    private long evalDuration;
}
