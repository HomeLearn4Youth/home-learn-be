package com.homelearn.back.house.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AddApartImgParam {
    private Long aptCode;
    private String aptImg;
}
