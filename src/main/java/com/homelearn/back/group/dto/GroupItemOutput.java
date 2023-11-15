package com.homelearn.back.group.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class GroupItemOutput {
    private Long groupId;
    private String groupName;
    private Long likeId;
    private Long aptCode;
    private String apartmentName; //아파트이름
    private String dong; // 아파트 동
    private String lng; //좌표
    private String lat; //좌표
}
