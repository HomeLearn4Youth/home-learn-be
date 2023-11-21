package com.homelearn.back.news.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class NaverNewsInput {
    private String lastBuildDate;
    private Integer total;
    private Integer start;
    private Integer display;
    private List<NaverNews> items;
}
