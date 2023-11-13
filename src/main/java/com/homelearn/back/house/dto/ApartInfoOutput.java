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
public class ApartInfoOutput {
    private Long aptId;
    private String aptName;
    private String sido;
    private String gugun;
    private String dong;
    private String aptRoadName;
    private String aptRoadNameBonbun;
    private String aptRoadNameBubun;
    private Boolean likeStatus;
    private String lng;
    private String lat;
}
