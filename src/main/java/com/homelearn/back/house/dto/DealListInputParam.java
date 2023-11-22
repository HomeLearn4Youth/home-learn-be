package com.homelearn.back.house.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class DealListInputParam {
    private Long apartCode; // 볼 아파트 코드
    private Integer startIndex; // 시작 번호
    private Integer count; //몇개 뽑을지
    private String type; //어떤 종류를 뽑을지(매매, 전월세) -> 없다면 모두
    private Integer bonbun;
    private Integer bubun;
    private String dongName;
}
