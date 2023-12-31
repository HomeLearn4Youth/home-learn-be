package com.homelearn.back.house.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.homelearn.back.house.entity.HouseJoinLike;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ApartOutputSpec {
    private Long aptId;
    private String aptName;
    private String dong;
    private Long bonbun;
    private Long bubun;
    private String aptRoadName;
    private Long aptRoadNameBonbun;
    private Long aptRoadNameBubun;
    private Boolean likeStatus; // 좋아요 상태
    private String lng;
    private String lat;


    public ApartOutputSpec houseJoinLikeToApartOutputSpec(HouseJoinLike m){
        return ApartOutputSpec.builder()
                .aptId(m.getAptCode())
                .aptName(m.getApartmentName())
                .dong(m.getDong())
                .bonbun(m.getBonbun())
                .bubun(m.getBubun())
                .aptRoadName(m.getRoadName())
                .aptRoadNameBonbun(m.getRoadNameBonbun())
                .aptRoadNameBubun(m.getRoadNameBubun())
                .likeStatus(m.getLikeStatus())
                .lng(m.getLng())
                .lat(m.getLat())
                .build();
    }



}
