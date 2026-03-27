package com.mua.ollama.net;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.mua.ollama.type.request.GenerateRequest;
import com.mua.ollama.type.response.GenerateResponse;
import com.mua.ollama.type.response.ListResponse;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

/**
 * Minimal native HTTP client using java.net.http.HttpClient to talk to Ollama, JSON serialization using Gson.
 */
public class OllamaHttpClient {
    private final String baseUrl;
    private final HttpClient client;
    private final Gson gson = new Gson();

    public OllamaHttpClient(String baseUrl) {
        if (baseUrl == null || baseUrl.isEmpty()) {
            throw new IllegalArgumentException("baseUrl cannot be null or empty");
        }
        this.baseUrl = baseUrl.endsWith("/") ? baseUrl.substring(0, baseUrl.length() - 1) : baseUrl;
        this.client = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(5))
                .build();
    }

    public GenerateResponse generate(GenerateRequest request) throws IOException, InterruptedException {
        String path = "/api/generate";
        String body = gson.toJson(request);

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(baseUrl + path))
                .header("Content-Type", "application/json")
                .timeout(Duration.ofSeconds(30))
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .build();

        HttpResponse<String> resp = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        int status = resp.statusCode();
        String respBody = resp.body();
        if (status >= 200 && status < 300) {
            try {
                return gson.fromJson(respBody, GenerateResponse.class);
            } catch (JsonSyntaxException ex) {
                throw new IOException("Failed to parse response JSON: " + ex.getMessage() + ", body=" + respBody, ex);
            }
        } else {
            throw new IOException("Non-2xx response: " + status + ", body=" + respBody);
        }
    }

    public ListResponse listModels() throws IOException, InterruptedException {
        String path = "/api/tags";
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(baseUrl + path))
                .header("Accept", "application/json")
                .timeout(Duration.ofSeconds(10))
                .GET()
                .build();

        HttpResponse<String> resp = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        int status = resp.statusCode();
        String respBody = resp.body();
        if (status >= 200 && status < 300) {
            try {
                return gson.fromJson(respBody, ListResponse.class);
            } catch (JsonSyntaxException ex) {
                throw new IOException("Failed to parse response JSON: " + ex.getMessage() + ", body=" + respBody, ex);
            }
        } else {
            throw new IOException("Non-2xx response from listModels: " + status + ", body=" + respBody);
        }
    }
}
