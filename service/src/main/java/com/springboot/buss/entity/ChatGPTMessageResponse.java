package com.springboot.buss.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatGPTMessageResponse {
    /**
     * id : chatcmpl-7NzrfgYtAcQ7eMs9eLTi7SYFPzVF4
     * object : chat.completion.chunk
     * created : 1685953707
     * model : gpt-3.5-turbo-0301
     * choices : [{"delta":{"content":"This"},"index":0,"finish_reason":null}]
     */

    private String id;
    private String object;
    private int created;
    private String model;
    private List<ChoicesBean> choices;
    @Data
    public static class ChoicesBean {
        /**
         * delta : {"content":"This"}
         * index : 0
         * finish_reason : null
         */

        private DeltaBean delta;
        private int index;
        private Object finish_reason;

        @Data
        public static class DeltaBean {
            /**
             * content : This
             */

            private String content;

        }
    }
}
