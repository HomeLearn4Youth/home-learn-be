package com.homelearn.back.house.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ApartListInputSpec {
    private Long dongCode; // 동코드
    private String searchApartName; // 아파트 이름 검색
    private Integer startIndex; // 시작 번호
    private Integer count; //몇개 뽑을지
    private Boolean isLike; //관심 영역만 출력 조건
    private Long groupId; //그룹 아이디 설정 조건
}
