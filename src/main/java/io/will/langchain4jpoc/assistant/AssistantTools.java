package io.will.langchain4jpoc.assistant;

import dev.langchain4j.agent.tool.Tool;
import io.micrometer.observation.annotation.Observed;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class AssistantTools {
    @Tool
    @Observed
    public String currentTime() {
        return LocalDateTime.now().toString();
    }
}
