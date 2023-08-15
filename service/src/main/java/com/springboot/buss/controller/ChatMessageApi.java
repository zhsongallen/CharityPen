package com.springboot.buss.controller;

import com.springboot.base.utils.LokiJsonUtils;
import com.springboot.buss.entity.RequestMsg;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.internal.sse.RealEventSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * @author loki
 */
@RestController
@CrossOrigin
@RequestMapping("message")
public class ChatMessageApi {

    @Autowired
    ChatMessageService chatMessageService;

    @PostMapping("/stream")
    public SseEmitter stream(@org.springframework.web.bind.annotation.RequestBody RequestMsg requestMsg) throws IOException, InterruptedException {
        SseEmitter emitter = new SseEmitter();

        ChatGPTRequest chatGPTRequest = ChatGPTRequest.builder().messages(Arrays.asList(ChatGPTRequest.ChatGPTRequestMessage.builder().role("user").content(requestMsg.getMsg()).build()))
                .temperature(0.7).model("gpt-3.5-turbo").stream(true).build();

        String str = LokiJsonUtils.toStr(chatGPTRequest);

        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), str);
        // 定义see接口
        Request request = new Request.Builder()
                .url("https://api.openai.com/v1/chat/completions")
                .addHeader("Content-Type","application/json")
                .addHeader("Authorization","Bearer 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")
                .post(requestBody).build();

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(1, TimeUnit.HOURS)
                .readTimeout(1, TimeUnit.HOURS)
//                .proxy(new Proxy(Proxy.Type.HTTP, new InetSocketAddress("127.0.0.1", 10809)))
                .build();

        // 实例化EventSource，注册EventSource监听器
        RealEventSource realEventSource = new RealEventSource(request, new ChatGPTMessageListener(emitter));
        //真正开始请求的一步
        realEventSource.connect(okHttpClient);
//        chatMessageService.test(emitter);
        return emitter;
    }

}
