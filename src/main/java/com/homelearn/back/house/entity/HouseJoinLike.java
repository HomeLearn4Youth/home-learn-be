package com.homelearn.back.house.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HouseJoinLike {
    //houseInfo
    private Long aptCode;

    private String buildYear;
    private String roadName;
    private Long roadNameBonbun;
    private Long roadNameBubun;
    private Long roadNameSeq;
    private Long roadNameBasementCode;
    private Long roadNameCode;
    private String dong;
    private Long bonbun;
    private Long bubun;
    private Long sigunguCode;
    private Long eubmyundongCode;
    private Long dongCode;
    private Long landCode;
    private String apartmentName;
    private String jibun;
    private String lng;
    private String lat;

    // 좋아요
    private Boolean likeStatus;
    private Long likeId;
    private Long userId;
}
