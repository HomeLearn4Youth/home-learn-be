package com.homelearn.back.news.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class NewsOutputSpec {
    private String title;
    private String link;
    private String thumbnailLink;
    private String mediaCompany;
    private String description;
    private String pubDate;

}
