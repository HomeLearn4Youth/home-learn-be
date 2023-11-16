package com.homelearn.back.review.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class DeleteReviewParam {
    private Long id;
    private Long userId;
}
