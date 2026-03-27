package com.mua.ollama.e2e;

import com.mua.ollama.net.OllamaHttpClient;
import com.mua.ollama.type.request.GenerateRequest;
import com.mua.ollama.type.response.GenerateResponse;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.URI;

import com.mua.ollama.type.response.ListResponse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class OllamaE2ETest {

    private static boolean isReachableViaTcp(String baseUrl) {
        try {
            URI uri = URI.create(baseUrl);
            String host = uri.getHost();
            int port = uri.getPort() == -1 ? (uri.getScheme().equals("https") ? 443 : 11434) : uri.getPort();
            try (Socket socket = new Socket()) {
                socket.connect(new InetSocketAddress(host, port), 1000);
                return true;
            }
        } catch (Exception e) {
            return false;
        }
    }

    @Test
    public void generateShouldReturnResponse() throws IOException, InterruptedException {
        String base = System.getenv().getOrDefault("OLLAMA_HOST", "http://localhost:11434");
        Assumptions.assumeTrue(isReachableViaTcp(base), "Ollama server not reachable at " + base + "; skipping E2E test");

        OllamaHttpClient client = new OllamaHttpClient(base);

        // pick a model available on the running Ollama instance; if none available, skip
        String envModel = System.getenv().get("OLLAMA_MODEL");
        String model;
        if (envModel != null && !envModel.isEmpty()) {
            model = envModel;
        } else {
            // fetch list of models
            ListResponse listResp = client.listModels();
            if (listResp == null || listResp.getModels() == null || listResp.getModels().isEmpty()) {
                Assumptions.assumeTrue(false, "No models available on Ollama instance; skipping E2E test");
                return; // unreachable but keeps compiler happy
            }
            model = listResp.getModels().get(0).getName();
        }

        GenerateRequest req = new GenerateRequest();
        req.setModel(model);
        req.setPrompt("E2E test: 2+2=?");
        req.setStream(false);

        GenerateResponse resp = client.generate(req);

        assertNotNull(resp, "Response should not be null");
        assertTrue((resp.getResponse() != null && !resp.getResponse().isEmpty()) || resp.isDone(), "Response should contain text or be done");
    }
}
