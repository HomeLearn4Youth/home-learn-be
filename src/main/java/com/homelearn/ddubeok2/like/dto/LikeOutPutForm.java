package com.homelearn.ddubeok2.like.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LikeOutPutForm {
    private Long id;
    private Long userId;
    private Long aptCode;
    private String apartmentName; //아파트이름
    private String dong; // 아파트 동
    private String lng; //좌표
    private String lat; //좌표
}
