package com.springboot.buss.controller;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.internal.sse.RealEventSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author loki
 */
@Controller
@CrossOrigin
@RequestMapping("messagenew")
public class ChatMessageApiNew {

    @Autowired
    ChatMessageService chatMessageService;

    @GetMapping("/stream")
    public SseEmitter stream1() throws IOException, InterruptedException {
        SseEmitter emitter = new SseEmitter();

        String str="{\n" +
                "    \"model\": \"gpt-3.5-turbo\",\n" +
                "    \"messages\": [\n" +
                "        {\n" +
                "            \"role\": \"user\",\n" +
                "            \"content\": \"Say this is a test!\"\n" +
                "        }\n" +
                "    ],\n" +
                "    \"temperature\": 0.7,\n" +
                "    \"stream\": true\n" +
                "}";




        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=utf-8"),str);
        // 定义see接口
        Request request = new Request.Builder()
                .url("https://api.openai.com/v1/chat/completions")
                .addHeader("Content-Type","application/json")
                .addHeader("Authorization","Bearer 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")
                .post(requestBody).build();

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(1, TimeUnit.HOURS)
                .readTimeout(1, TimeUnit.HOURS)
                .proxy(new Proxy(Proxy.Type.HTTP, new InetSocketAddress("127.0.0.1", 10809)))
                .build();

        // 实例化EventSource，注册EventSource监听器
        RealEventSource realEventSource = new RealEventSource(request, new ChatGPTMessageListener(emitter));
        //真正开始请求的一步
        realEventSource.connect(okHttpClient);
//        chatMessageService.test(emitter);
        return emitter;
    }
    @PostMapping("/stream")
    public SseEmitter stream() throws IOException, InterruptedException {
        SseEmitter emitter = new SseEmitter();

        chatMessageService.test(emitter);
        return emitter;
    }

    public static void main(String[] args) {

        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();

        for (int i = 0; i < 5; i++) {
            final int index = i;
            cachedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        for (int i = 0; i < 10; i++) {
                            System.out.println("i = " + i);
                        }
                    } catch (Exception e) {
                    }
                }
            });
        }


//        for (int i = 1; i <= 10; i++) {
//            Thread thread = new Thread(new RandomNumberGenerator());
//            thread.start();
//        }
    }

    static class RandomNumberGenerator implements Runnable {
        private final Random random = new Random();

        @Override
        public void run() {
                System.out.println(Thread.currentThread().getName() + ": " + random.nextInt(100));
        }
    }
}
