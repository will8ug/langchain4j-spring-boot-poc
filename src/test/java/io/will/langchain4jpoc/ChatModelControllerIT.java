package io.will.langchain4jpoc;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ChatModelControllerIT {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void modelEndpoint_givenCustomMessage_whenCallEndpoint_thenReturnSuccessfulResponse() {
        webTestClient.get()
                .uri("/model?message=What is the capital of France?")
                .accept(MediaType.TEXT_PLAIN)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.TEXT_PLAIN + ";charset=UTF-8")
                .expectBody(String.class)
                .value(response -> {
                    assertNotNull(response);
                    assertFalse(response.isBlank());
                    assertTrue(response.contains("Paris"));
                });
    }
}