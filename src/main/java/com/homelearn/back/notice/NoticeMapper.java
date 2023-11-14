package com.homelearn.back.notice;

import com.homelearn.back.notice.dto.AddNoticeInputSpec;
import com.homelearn.back.notice.dto.EditNoticeInputSpec;
import com.homelearn.back.notice.dto.FindListNoticeInputSpec;
import com.homelearn.back.notice.entity.Notice;
import com.homelearn.back.notice.entity.NoticeJoinMember;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NoticeMapper {
    //Create
    void addNotice(AddNoticeInputSpec addNoticeInputSpec);
    //Read
    NoticeJoinMember getNoticeById(Long noticeId);
    List<NoticeJoinMember> getNoticeList(FindListNoticeInputSpec findListNoticeInputSpec);
    //Update
    void editNotice(EditNoticeInputSpec editNoticeInputSpec);
    void countNotice(Long noticeId);
    //Delete
    void deleteNoticeById(Long noticeId);
}
