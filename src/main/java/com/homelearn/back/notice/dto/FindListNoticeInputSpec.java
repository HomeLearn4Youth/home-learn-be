package com.homelearn.back.notice.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class FindListNoticeInputSpec {
    private String searchText; // 검색어
    private Integer startIndex; // 시작 번호
    private Integer count; //몇개 뽑을지
}
