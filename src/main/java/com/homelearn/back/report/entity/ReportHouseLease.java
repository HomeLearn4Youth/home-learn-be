package com.homelearn.back.report.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReportHouseLease {
    private int aptCode;// 아파트 코드
    private String dongCode; //시군구동 코드
    private String sigu; //시군구 코드
    private String siguName; //시군구 명
    private String dong; // 동 코드
    private String dongName; // 동 이름
    private String bonbun;//본번
    private String bubun;//부번
    private String floor; //층
    private String dealDate; // 날짜형식
    private String area; //전용 면적
    private String deposit; //보증금 or 전세 금액
    private String apartmentName; //아파트 이름
    private String buildYear;//설립 연도
    private String termContract;//계약기간
    private String previousDeposit; // 종전 계약 보증금
    private String dealYear; //계약 년도
    private String dealMonth; // 계약 월
}
