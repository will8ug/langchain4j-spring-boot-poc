# LangChain4j Spring Boot POC

A Proof of Concept (POC) project demonstrating the integration of LangChain4j with Spring Boot for building AI-powered applications.

## Overview

This project showcases how to use LangChain4j, a Java framework for building applications with Large Language Models (LLMs), integrated with Spring Boot to create a RESTful AI assistant service.

## Running the Application

### Development Mode
```bash
./mvnw spring-boot:run
```

The application will start on `http://localhost:8080`

## API Endpoints

### Chat with AI Assistant
**POST** `/assistant`

Request body:
```json
{
  "message": "Hello, how are you?"
}
```

Response:
```json
{
  "response": "Hello! I'm doing well, thank you for asking. How can I help you today?"
}
```

## Example Usage

### Using curl
```bash
# Health check
curl http://localhost:8080/health

# Chat with AI
curl -X POST http://localhost:8080/assistant/streaming \
  -H "Content-Type: application/json" \
  -d '{"message": "What is the capital of France?"}'
```

### Using a REST client
Send a POST request to `http://localhost:8080/assistant/streaming` with the following JSON body:
```json
{
  "message": "Explain quantum computing in simple terms"
}
```

## References

- [LangChain4j Documentation](https://github.com/langchain4j/langchain4j)
- [Spring Boot Documentation](https://spring.io/projects/spring-boot)
