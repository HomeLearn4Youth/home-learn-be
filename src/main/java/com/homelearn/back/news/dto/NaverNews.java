package com.homelearn.back.news.dto;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class NaverNews {
    private String title;
    private String originallink;
    private String link;
    private String description;
    private String pubDate;

}
