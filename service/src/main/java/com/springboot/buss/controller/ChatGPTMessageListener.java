package com.springboot.buss.controller;

import com.springboot.base.utils.LokiJsonUtils;
import com.springboot.buss.entity.ChatGPTMessageResponse;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Response;
import okhttp3.sse.EventSource;
import okhttp3.sse.EventSourceListener;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.Objects;

@Slf4j
public class ChatGPTMessageListener extends EventSourceListener {
    private SseEmitter sseEmitter;

    public ChatGPTMessageListener(SseEmitter sseEmitter) {
        this.sseEmitter = sseEmitter;
    }
    @Override
    public void onOpen(EventSource eventSource, Response response) {
        log.info("onOpen");
    }

    @Override
    public void onEvent(EventSource eventSource, String id, String type, String data) {
        try {
            if (Objects.equals(data,"[DONE]")) {
                log.info("[DONE]");
                sseEmitter.send("[DONE]");
                sseEmitter.complete();
                return;
            }
            if(!data.contains("content")){
                return;
            }
//            ObjectMapper mapper = new ObjectMapper();
//            ChatGPTMessageResponse completionResponse = mapper.readValue(data, ChatGPTMessageResponse.class);

            ChatGPTMessageResponse completionResponse = LokiJsonUtils.strToObj(data, ChatGPTMessageResponse.class);

            sseEmitter.send(completionResponse.getChoices().get(0).getDelta());
        } catch (IOException e) {
            log.error("sse信息推送失败！");
            eventSource.cancel();
            e.printStackTrace();
        }
    }

    @Override
    public void onClosed(EventSource eventSource) {
        log.info("onClosed");
    }

    @Override
    public void onFailure(EventSource eventSource, Throwable t, Response response) {
        log.error("onFailure",t);
    }
}
