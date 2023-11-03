package com.homelearn.ddubeok2.notice;

import com.homelearn.ddubeok2.notice.dto.NoticeForm;

import java.util.List;

public interface NoticeService {
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
