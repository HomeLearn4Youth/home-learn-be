package com.homelearn.back.news.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class NaverImgInputSpec {
    private String lastBuildDate;
    private Integer total;
    private Integer start;
    private Integer display;
    private List<NaverImgs> items;
}
