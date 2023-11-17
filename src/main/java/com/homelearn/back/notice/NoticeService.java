package com.homelearn.back.notice;

import com.homelearn.back.notice.dto.*;
import com.homelearn.back.notice.entity.Notice;
import com.homelearn.back.notice.entity.NoticeJoinMember;

import java.util.List;

public interface NoticeService {
    //Create
    void addNotice(AddNoticeInputSpec addNoticeInputSpec);
    //Read
    NoticeJoinMember getNoticeById(Long noticeId);
    List<Notice> getNoticeList(FindListNoticeInputSpec findListNoticeInputSpec);
    //Update
    void editNotice(EditNoticeInputSpec editNoticeForm);
    //Delete
    void deleteNoticeById(Long noticeId);

}
