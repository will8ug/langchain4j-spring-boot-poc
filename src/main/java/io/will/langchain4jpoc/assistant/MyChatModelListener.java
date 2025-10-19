package io.will.langchain4jpoc.assistant;

import dev.langchain4j.model.chat.listener.ChatModelErrorContext;
import dev.langchain4j.model.chat.listener.ChatModelListener;
import dev.langchain4j.model.chat.listener.ChatModelRequestContext;
import dev.langchain4j.model.chat.listener.ChatModelResponseContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyChatModelListener implements ChatModelListener {
    private static final Logger logger = LoggerFactory.getLogger(MyChatModelListener.class);

    @Override
    public void onRequest(ChatModelRequestContext requestContext) {
        logger.info("onRequest(): {}", requestContext.chatRequest());
    }

    @Override
    public void onResponse(ChatModelResponseContext responseContext) {
        logger.info("onResponse(): {}", responseContext.chatResponse());
    }

    @Override
    public void onError(ChatModelErrorContext errorContext) {
        logger.error("onError(): {}", errorContext.error().getMessage());
    }
}
