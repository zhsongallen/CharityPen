package com.springboot.buss.controller;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatGPTRequest {
    private String model;
    private Double temperature;
    private Boolean stream;
    private List<ChatGPTRequestMessage> messages;
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class ChatGPTRequestMessage{
        private String role;
        private String content;
    }
}
