package com.homelearn.back.notice;

import com.homelearn.back.notice.dto.NoticeForm;
import com.homelearn.back.notice.entity.Notice;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NoticeMapper {
    //Create
    void addNotice(NoticeForm noticeForm);
    //Read
    Notice getNoticeById(Long noticeId);
    List<Notice> getNoticeList();
    //Update
    void editNotice(NoticeForm noticeForm);
    void countNotice(Long noticeId);
    //Delete
    void deleteNoticeById(Long noticeId);
}
