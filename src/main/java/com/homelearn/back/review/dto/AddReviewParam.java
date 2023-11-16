package com.homelearn.back.review.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AddReviewParam {
    private String content;
    private Long aptCode;
    private Long userId;
}
