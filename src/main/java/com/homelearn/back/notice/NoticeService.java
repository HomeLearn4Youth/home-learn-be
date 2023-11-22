package com.homelearn.back.notice;

import com.homelearn.back.notice.dto.*;
import com.homelearn.back.notice.entity.Notice;
import com.homelearn.back.notice.entity.NoticeJoinMember;
import com.homelearn.back.user.entity.User;

import java.util.List;

public interface NoticeService {
    //Create
    void addNotice(AddNoticeInputSpec input, User loginUser);
    //Read
    NoticeJoinMember getNoticeById(Long noticeId);
    List<Notice> getNoticeList(FindListNoticeInputSpec inputSpec);
    //Update
    void editNotice(EditNoticeInputSpec input, User loginUser);
    //Delete
    void deleteNoticeById(Long noticeId, User loginUser);
    Integer getTotalCount();
}
