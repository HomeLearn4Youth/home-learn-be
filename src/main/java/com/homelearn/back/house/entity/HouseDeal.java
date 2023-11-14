package com.homelearn.back.house.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HouseDeal {
    private Long no;
    private String dealAmount;
    private Integer dealYear;
    private Integer dealMonth;
    private Integer dealDay;
    private String area;
    private String floor;
    private String cancelDealType;
    private Long aptCode;
}
