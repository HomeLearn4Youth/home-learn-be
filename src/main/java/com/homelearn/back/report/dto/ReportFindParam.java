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
public class ReportFindParam { // 전세 아파트 거래내역 상세조회
    private int aptCode; // 아파트 코드

    public ReportFindParam reportFindInputSpecToReportFindParam (ReportFindInputSpec inputSpec){
        return ReportFindParam.builder()
                .aptCode(inputSpec.getAptCode())
                .build();
    }
}
