package com.springboot.buss.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author loki
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class LoginDTO {
    private String name;
    private String password;
}
