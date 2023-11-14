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
public class FindNoticeOutputSpec {
    private Long id;
    private String title;
    private String content;
    private int viewCount;
    private String creatTime;
    private Long writerId;
    private String writerEmail;
    private String writerName;

    public FindNoticeOutputSpec noticeJoinMemberToFindOutputSpec(NoticeJoinMember noticeJoinMember){
        return FindNoticeOutputSpec.builder()
                .id(noticeJoinMember.getId())
                .title(noticeJoinMember.getTitle())
                .content(noticeJoinMember.getContent())
                .viewCount(noticeJoinMember.getViewCount())
                .creatTime(noticeJoinMember.getCreatTime())
                .writerId(noticeJoinMember.getWriterId())
                .writerEmail(noticeJoinMember.getWriterEmail())
                .writerName(noticeJoinMember.getWriterName())
                .build();
    }
}
