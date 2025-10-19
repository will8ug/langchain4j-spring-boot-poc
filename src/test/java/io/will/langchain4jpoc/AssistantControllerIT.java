package io.will.langchain4jpoc;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AssistantControllerIT {
    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testAssistant_givenMessage_whenCallEndpoint_thenReturnSuccessfulResponse() {
        webTestClient.post()
                .uri("/assistant")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue("{\"message\": \"What is the capital of China?\"}")
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .value(response -> {
                    assertNotNull(response);
                    assertTrue(response.contains("Beijing"));
                });
    }

    @Test
    void testAssistantStreaming_givenMessage_whenCallEndpoint_thenReturnFluxResponse() {
        webTestClient.post()
                .uri("/assistant/streaming")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue("{\"message\": \"What is the capital of France?\"}")
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentTypeCompatibleWith(MediaType.TEXT_EVENT_STREAM_VALUE)
                .returnResult(String.class)
                .getResponseBody()
                .take(3)  // 只取前3个流事件来验证
                .doOnNext(chunk -> {
                    assertNotNull(chunk);
                    assertFalse(chunk.isEmpty());
                })
                .blockLast();
    }
}
