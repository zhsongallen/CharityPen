package com.springboot.buss.controller;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;

@Service
public class ChatMessageService {
    @Async
    public void test(SseEmitter emitter) throws IOException, InterruptedException {
        for (int i = 0; i < 10; i++) {
            emitter.send(i);
            Thread.sleep(1000);
        }
        emitter.send("[close]");
        emitter.complete();
    }
}
