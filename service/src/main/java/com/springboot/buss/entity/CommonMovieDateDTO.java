package com.springboot.buss.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author loki
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommonMovieDateDTO {
    private MovieDTO currentMovie;
    private MovieDTO recentMovie;
}
