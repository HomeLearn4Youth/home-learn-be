package com.homelearn.ddubeok2.house;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Apart {
    //housedeal
    private Long no;
    private Integer dealAmount;
    private Integer dealYear;
    private Integer dealMonth;
    private Integer dealDay;
    private String area;
    private String floor;
    private String cancelDealType;
    private Long aptCode;
    //houseinfo
    private Integer buildYear;
    private String roadName;
    private String roadNameBonbun;
    private String roadNameBubun;
    private String roadNameSeq;
    private String roadNameBasementCode;
    private String roadNameCode;
    private String dong;
    private String bonbun;
    private String bubun;
    private String sigunguCode;
    private String eubmyeondongCode;
    private String dongCode;
    private String landCode;
    private String apartmentName;
    private String jibun;
    private String lng;
    private String lat;
}
