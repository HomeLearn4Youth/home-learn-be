package com.homelearn.back.report.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.homelearn.back.notice.dto.FindNoticeOutputSpec;
import com.homelearn.back.notice.entity.NoticeJoinMember;
import com.homelearn.back.report.entity.RentHouseInfo;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class LApartListOutputSpec {
    private int aptCode;
    private String apartmentName;
    public LApartListOutputSpec rentHouseInfoToLApartListOutputSpec(RentHouseInfo rentHouseInfo){
        return LApartListOutputSpec.builder()
                .aptCode(rentHouseInfo.getAptCode())
                .apartmentName(rentHouseInfo.getApartmentName())
                .build();
    }
}
