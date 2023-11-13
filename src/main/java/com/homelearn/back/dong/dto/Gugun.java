package com.homelearn.back.dong.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Gugun {
    private String gugunName;
    private String gugunCode;
}
