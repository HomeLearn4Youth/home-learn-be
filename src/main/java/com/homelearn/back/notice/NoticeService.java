package com.homelearn.back.notice;

import com.homelearn.back.notice.dto.*;
import com.homelearn.back.notice.entity.Notice;
import com.homelearn.back.notice.entity.NoticeJoinMember;

import java.util.List;

public interface NoticeService {
    //Create
    void addNotice(NoticeParam param);
    //Read
    NoticeJoinMember getNoticeById(Long noticeId);
    List<Notice> getNoticeList(FindListNoticeInputSpec findListNoticeInputSpec);
    //Update
    void editNotice(NoticeParam param);
    //Delete
    void deleteNoticeById(Long noticeId);

}
