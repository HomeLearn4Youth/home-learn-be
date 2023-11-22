package com.homelearn.back.notice.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class FindListNoticeOutputSpec {
    private String requestSearchText; // 검색어
    private Integer requestStartIndex; // 시작 번호
    private Integer requestCount; //몇개 뽑을지
    private Integer totalCount; // 총
    private List<FindListNoticeItemOutputSpec> items;
}
