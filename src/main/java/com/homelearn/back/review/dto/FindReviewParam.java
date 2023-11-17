package com.homelearn.back.review.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FindReviewParam {
    private Long id;
    private Long aptCode;
    private String content;
    private Long userId;
}
