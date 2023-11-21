package com.homelearn.back.report.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AvgSquareMeter {
    private String regionCode;
    private String sidoCode;
    private String sidoName;
    private String date;
    private String leaseAvgPrice;
    private String dealAvgPrice;
    private String year;
    private String month;
}
