# Ollama Java

A Java library providing type-safe models and request/response classes for the [Ollama](https://ollama.ai/) API. This library makes it easy to integrate Ollama's local LLM capabilities into your Java applications.

[![Build](https://github.com/maifeeulasad/ollama-java/actions/workflows/build.yml/badge.svg)](https://github.com/maifeeulasad/ollama-java/actions/workflows/build.yml)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

## Features

- 🎯 **Type-safe models** - Strongly typed Java classes for all Ollama API requests and responses
- 🔧 **Complete API coverage** - Support for all Ollama API endpoints including:
  - Text generation (`/api/generate`)
  - Chat completion (`/api/chat`)
  - Model management (list, show, create, delete, copy, pull, push)
  - Embeddings generation (`/api/embed`, `/api/embeddings`)
  - Tool/function calling support
- 📦 **Zero runtime dependencies** - Only uses Gson for JSON serialization and Lombok for cleaner code (compile-time only)
- ☕ **Java 8+** - Compatible with Java 8 and above
- 🚀 **Lightweight** - Minimal footprint, just the type definitions you need

## Installation

### Maven

Add the dependency to your `pom.xml`:

```xml
<dependency>
    <groupId>com.mua.ollama</groupId>
    <artifactId>ollama-java</artifactId>
    <version>0.0.0</version>
</dependency>
```

You'll also need to configure GitHub Packages as a repository:

```xml
<repositories>
    <repository>
        <id>github</id>
        <url>https://maven.pkg.github.com/maifeeulasad/ollama-java</url>
    </repository>
</repositories>
```

### Gradle

```gradle
repositories {
    maven {
        url = uri("https://maven.pkg.github.com/maifeeulasad/ollama-java")
        credentials {
            username = project.findProperty("gpr.user") ?: System.getenv("USERNAME")
            password = project.findProperty("gpr.key") ?: System.getenv("TOKEN")
        }
    }
}

dependencies {
    implementation 'com.mua.ollama:ollama-java:0.0.0'
}
```

## Usage

This library provides type-safe Java classes for building requests and parsing responses from the Ollama API. You'll need to implement the HTTP client layer yourself.

### Example: Generate Request

```java
import com.mua.ollama.type.request.GenerateRequest;
import com.mua.ollama.type.response.GenerateResponse;
import com.mua.ollama.type.Options;

// Create a generate request
GenerateRequest request = new GenerateRequest();
request.setModel("llama2");
request.setPrompt("Why is the sky blue?");
request.setStream(false);

// Configure generation options
Options options = new Options();
options.setTemperature(0.7);
options.setTopP(0.9);
request.setOptions(options);

// Send request to Ollama API (HTTP client implementation required)
// POST http://localhost:11434/api/generate
// Parse response into GenerateResponse object
```

### Example: Chat Request

```java
import com.mua.ollama.type.request.ChatRequest;
import com.mua.ollama.type.request.Message;
import com.mua.ollama.type.response.ChatResponse;

import java.util.Arrays;

// Create messages
Message systemMsg = new Message();
systemMsg.setRole("system");
systemMsg.setContent("You are a helpful assistant.");

Message userMsg = new Message();
userMsg.setRole("user");
userMsg.setContent("What is the capital of France?");

// Create chat request
ChatRequest request = new ChatRequest();
request.setModel("llama2");
request.setMessages(Arrays.asList(systemMsg, userMsg));
request.setStream(false);

// Send request to Ollama API (HTTP client implementation required)
// POST http://localhost:11434/api/chat
// Parse response into ChatResponse object
```

### Example: Embeddings Request

```java
import com.mua.ollama.type.request.EmbedRequest;
import com.mua.ollama.type.response.EmbedResponse;

// Create embeddings request
EmbedRequest request = new EmbedRequest();
request.setModel("llama2");
request.setInput("The quick brown fox jumps over the lazy dog");

// Send request to Ollama API (HTTP client implementation required)
// POST http://localhost:11434/api/embed
// Parse response into EmbedResponse object
```

### Example: List Models

```java
import com.mua.ollama.type.response.ListResponse;
import com.mua.ollama.type.response.ModelResponse;

// Send GET request to Ollama API (HTTP client implementation required)
// GET http://localhost:11434/api/tags
// Parse response into ListResponse object

// Access model information
for (ModelResponse model : listResponse.getModels()) {
    System.out.println("Model: " + model.getName());
    System.out.println("Size: " + model.getSize());
    System.out.println("Modified: " + model.getModifiedAt());
}
```

### Example: Tool/Function Calling

```java
import com.mua.ollama.type.request.Tool;
import com.mua.ollama.type.request.ToolFunction;
import com.mua.ollama.type.request.ToolParameters;
import com.mua.ollama.type.request.ToolProperty;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

// Define a function tool
ToolFunction function = new ToolFunction();
function.setName("get_weather");
function.setDescription("Get the current weather for a location");

// Define parameters
ToolParameters params = new ToolParameters();
params.setType("object");
params.setRequired(Arrays.asList("location"));

Map<String, ToolProperty> properties = new HashMap<>();
ToolProperty locationProp = new ToolProperty();
locationProp.setType("string");
locationProp.setDescription("The city and state, e.g. San Francisco, CA");
properties.put("location", locationProp);
params.setProperties(properties);

function.setParameters(params);

// Create tool
Tool tool = new Tool();
tool.setType("function");
tool.setFunction(function);

// Add to chat request
ChatRequest request = new ChatRequest();
request.setTools(Arrays.asList(tool));
// ... set other properties
```

## API Reference

### Request Types

| Class | Description | Endpoint |
|-------|-------------|----------|
| `GenerateRequest` | Generate text completion | `/api/generate` |
| `ChatRequest` | Chat completion with message history | `/api/chat` |
| `EmbedRequest` | Generate embeddings for input text | `/api/embed` |
| `EmbeddingsRequest` | Generate embeddings (legacy) | `/api/embeddings` |
| `CreateRequest` | Create a new model from a Modelfile | `/api/create` |
| `ShowRequest` | Show model information | `/api/show` |
| `CopyRequest` | Copy a model | `/api/copy` |
| `DeleteRequest` | Delete a model | `/api/delete` |
| `PullRequest` | Pull a model from registry | `/api/pull` |
| `PushRequest` | Push a model to registry | `/api/push` |

### Response Types

| Class | Description |
|-------|-------------|
| `GenerateResponse` | Response from text generation |
| `ChatResponse` | Response from chat completion |
| `EmbedResponse` | Response with embeddings |
| `EmbeddingsResponse` | Response with embedding (legacy) |
| `ListResponse` | List of available models |
| `ModelResponse` | Model metadata |
| `ShowResponse` | Detailed model information |
| `ProgressResponse` | Progress update (for pull/push/create) |
| `StatusResponse` | Status information |
| `ErrorResponse` | Error details |

### Supporting Types

- `Message` - Chat message with role, content, images, and tool calls
- `Options` - Model configuration options (temperature, top_p, etc.)
- `Tool` - Function/tool definition for function calling
- `ToolFunction` - Function specification
- `ToolParameters` - Function parameter schema
- `ModelDetails` - Model metadata details
- `Config` - Configuration for HTTP client (future use)

## Building from Source

### Prerequisites

- Java 8 or higher
- Maven 3.6+

### Build

```bash
# Clone the repository
git clone https://github.com/maifeeulasad/ollama-java.git
cd ollama-java

# Build the project
mvn clean package

# Install to local Maven repository
mvn clean install
```

### Project Structure

```
ollama-java/
├── src/main/java/com/mua/ollama/
│   ├── Main.java                    # Demo/example class
│   └── type/
│       ├── Options.java             # Model configuration options
│       ├── core/
│       │   └── Config.java          # HTTP client configuration
│       ├── request/                 # Request models
│       │   ├── ChatRequest.java
│       │   ├── GenerateRequest.java
│       │   ├── EmbedRequest.java
│       │   └── ...
│       └── response/                # Response models
│           ├── ChatResponse.java
│           ├── GenerateResponse.java
│           ├── EmbedResponse.java
│           └── ...
├── pom.xml
└── README.md
```

## Contributing

Contributions are welcome! Please feel free to submit a Pull Request. For major changes, please open an issue first to discuss what you would like to change.

### Development Guidelines

1. Follow existing code style
2. Use Lombok annotations for cleaner code
3. Ensure all fields match the [Ollama API specification](https://github.com/ollama/ollama/blob/main/docs/api.md)
4. Test your changes with Maven: `mvn clean package`

## Roadmap

- [ ] Implement HTTP client layer
- [ ] Add streaming response support
- [ ] Add async/reactive API support
- [ ] Add comprehensive examples
- [ ] Add unit tests
- [ ] Publish to Maven Central

## Related Projects

- [Ollama](https://github.com/ollama/ollama) - The official Ollama project
- [Ollama Python](https://github.com/ollama/ollama-python) - Official Python library
- [Ollama JavaScript](https://github.com/ollama/ollama-js) - Official JavaScript library

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Acknowledgments

- Thanks to the [Ollama](https://ollama.ai/) team for creating an amazing local LLM platform
- Built with [Lombok](https://projectlombok.org/) for cleaner Java code
- Uses [Gson](https://github.com/google/gson) for JSON serialization

## Support

If you encounter any issues or have questions, please [open an issue](https://github.com/maifeeulasad/ollama-java/issues) on GitHub.

---

**Note**: This library currently provides only type definitions. You'll need to implement the HTTP client layer to make actual API calls to Ollama. A complete HTTP client implementation is planned for future releases.
