package com.mua.ollama.type;

import lombok.Data;

import java.util.List;

@Data
public class Options {
    private boolean numa;
    private int numCtx;
    private int numBatch;
    private int numGpu;
    private int mainGpu;
    private boolean lowVram;
    private boolean f16Kv;
    private boolean logitsAll;
    private boolean vocabOnly;
    private boolean useMmap;
    private boolean useMlock;
    private boolean embeddingOnly;
    private int numThread;

    private int numKeep;
    private int seed;
    private int numPredict;
    private int topK;
    private double topP;
    private double tfsZ;
    private double typicalP;
    private int repeatLastN;
    private double temperature;
    private double repeatPenalty;
    private double presencePenalty;
    private double frequencyPenalty;
    private int mirostat;
    private double mirostatTau;
    private double mirostatEta;
    private boolean penalizeNewline;
    private List<String> stop;
}
