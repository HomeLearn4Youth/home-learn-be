package com.homelearn.back.notice.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.homelearn.back.notice.entity.Notice;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class FindListNoticeItemOutputSpec {
    private Long noticeId;
    private String title;
    private String time;

    public FindListNoticeItemOutputSpec noticeToFindListOutputSpec(Notice notice){
        return FindListNoticeItemOutputSpec.builder()
                .noticeId(notice.getId())
                .title(notice.getTitle())
                .time(notice.getCreatTime())
                .build();
    }
}
