package com.homelearn.back.house.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ApartDealOutput {
    private Long aptCode;
    private String type;
    private Integer dealYear;
    private Integer dealMonth;
    private Integer dealDay;
    private String area;
    private String floor;
    private Integer dealAmount;
}