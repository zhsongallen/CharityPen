package com.springboot.base.vo;

import lombok.Data;

/**
 * @author loki
 */
@Data
public class PageVO {
    private Integer current;
    private Integer size;
    private String keyword;
}
