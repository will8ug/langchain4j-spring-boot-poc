package io.will.langchain4jpoc.chatmodel;

import dev.langchain4j.model.chat.ChatModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class ChatModelController {
    private final ChatModel chatModel;

    public ChatModelController(ChatModel chatModel) {
        this.chatModel = chatModel;
    }

    @GetMapping("/model")
    public Mono<String> model(@RequestParam(value = "message", defaultValue = "Hello!") String message) {
        return Mono.fromSupplier(() -> chatModel.chat(message))
                .subscribeOn(reactor.core.scheduler.Schedulers.boundedElastic());
    }
}