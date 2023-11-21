package com.homelearn.back.report.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.homelearn.back.report.entity.ReportHouseLease;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ReportFindOutputSpec {
    private int aptCode; //아파트 코드
    private String apartmentName; //아파트명
    private String dongCode; // 시군구동코드
    private String sigu; // 시구코드
    private String dong; // 동코드
    private String siguName; // 시구 명
    private String dongName; // 동 명
    private String buildYear; // 건축연도
    private String bonbun; // 본번
    private String bubun; // 부번
    private String floor; // 층
    private String dealDate; // 거래일자
    private String dealYear; // 거래 연도
    private String dealMonth; // 거래 월
    private String area; // 전용면적
    private String deposit; // 보증금
    private String termContracet; // 계약기간
    private String previousDeposit; // 이전 거래 가격

    public ReportFindOutputSpec reportFindInputSpecToReportFindParam(ReportHouseLease m) {
        return ReportFindOutputSpec.builder()
                .aptCode(m.getAptCode())
                .apartmentName(m.getApartmentName())
                .dongCode(m.getDongCode())
                .sigu(m.getSigu())
                .dong(m.getDong())
                .siguName(m.getSiguName())
                .dongName(m.getDongName())
                .buildYear(m.getBuildYear())
                .bonbun(m.getBonbun())
                .bubun(m.getBubun())
                .floor(m.getFloor())
                .dealDate(m.getDealDate())
                .dealYear(m.getDealYear())
                .dealMonth(m.getDealMonth())
                .area(m.getArea())
                .deposit(m.getDeposit())
                .termContracet(m.getTermContract())
                .previousDeposit(m.getPreviousDeposit())
                .build();
    }
}