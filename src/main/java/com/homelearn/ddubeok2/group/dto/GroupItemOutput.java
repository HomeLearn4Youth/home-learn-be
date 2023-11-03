package com.homelearn.ddubeok2.group.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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
