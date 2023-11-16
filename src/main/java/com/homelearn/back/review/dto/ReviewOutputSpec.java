package com.homelearn.back.review.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.homelearn.back.user.UserRole;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ReviewOutputSpec {
    private Long writerId;

    private Long reviewId;
    private String content;
    private String createTime;

    private String writerName;

}
