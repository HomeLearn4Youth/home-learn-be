package com.homelearn.back.house.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HouseLease {
    private Long no;
    private Long aptCode;// 아파트 코드
    private String buildYear;//설립 연도
    private String dong; //동
    private String jibun;//지번
    private String apartmentName; //아파트 이름
    private String regionalCode; //시군구 코드
    private String field;//계약기간
    private String deposit; //보증금 or 전세 금액
    private int dealYear; //계약 년도
    private int dealMonth; // 계약 월
    private int dealDay; // 계약 일
    private String monthlyRent; // 월세 금액
    private String areaForExclusiveUse; //전용 면적
    private String previousDeposit; // 종전 계약 보증금
    private String previousMonthlyRent; // 종전 계약 월세
    private String floor; //층
}
