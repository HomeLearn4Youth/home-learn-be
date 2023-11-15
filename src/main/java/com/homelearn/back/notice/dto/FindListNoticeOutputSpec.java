package com.homelearn.back.notice.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.homelearn.back.notice.entity.Notice;
import com.homelearn.back.notice.entity.NoticeJoinMember;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class FindListNoticeOutputSpec {
    private Long noticeId;
    private String title;
    private String time;

    public FindListNoticeOutputSpec noticeToFindListOutputSpec(Notice notice){
        return FindListNoticeOutputSpec.builder()
                .noticeId(notice.getId())
                .title(notice.getTitle())
                .time(notice.getCreatTime())
                .build();
    }
}
