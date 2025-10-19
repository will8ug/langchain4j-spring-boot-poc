package io.will.langchain4jpoc.assistant;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
public class AssistantController {

    private final Assistant assistant;
    private final StreamingAssistant streamingAssistant;

    public AssistantController(Assistant assistant, StreamingAssistant streamingAssistant) {
        this.assistant = assistant;
        this.streamingAssistant = streamingAssistant;
    }

    @PostMapping("/assistant")
    public Mono<String> assistant(@RequestBody Map<String, String> request) {
        String message = request.get("message");
        if (message == null || message.trim().isEmpty()) {
            return Mono.error(new IllegalArgumentException("Message cannot be empty"));
        }

        return Mono.fromSupplier(() -> assistant.chat(message))
                .subscribeOn(Schedulers.boundedElastic());
    }

    @PostMapping(value = "/assistant/streaming", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> assistantStreaming(@RequestBody Map<String, String> request) {
        return streamingAssistant.chat(request.get("message"));
    }
}
