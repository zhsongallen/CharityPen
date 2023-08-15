package com.springboot.buss.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author loki
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReplyDTO {

    private String replyContent;

    private String replyName;
    @JsonFormat(pattern = "MM-dd,HH:mm")
    private Date replyTime;
}