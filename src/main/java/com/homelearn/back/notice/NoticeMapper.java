package com.homelearn.back.notice;

import com.homelearn.back.notice.dto.AddNoticeInputSpec;
import com.homelearn.back.notice.dto.EditNoticeInputSpec;
import com.homelearn.back.notice.dto.FindListNoticeInputSpec;
import com.homelearn.back.notice.dto.NoticeParam;
import com.homelearn.back.notice.entity.Notice;
import com.homelearn.back.notice.entity.NoticeJoinMember;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface NoticeMapper {
    //Create
    void addNotice(NoticeParam param);
    //Read
    Optional<NoticeJoinMember> getNoticeById(Long noticeId);
    List<Notice> getNoticeList(FindListNoticeInputSpec findListNoticeInputSpec);
    //Update
    void editNotice(NoticeParam param);
    void countNotice(Long noticeId);
    //Delete
    void deleteNoticeById(Long noticeId);

    Integer getTotalCount();
}
