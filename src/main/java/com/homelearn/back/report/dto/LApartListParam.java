package com.homelearn.back.report.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class LApartListParam { // 전세 아파트 리스트 조회
    private String dongCode; // 동코드

    public LApartListParam rentApartListInputSpecToRentApartListParam (LApartListInputSpec inputSpec){
        return LApartListParam.builder()
                .dongCode(inputSpec.getDongCode())
                .build();
    }
}
