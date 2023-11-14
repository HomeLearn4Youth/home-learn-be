package com.homelearn.back.notice;

import com.homelearn.back.notice.dto.*;

import java.util.List;

public interface NoticeService {
    //Create
    void addNotice(AddNoticeInputSpec addNoticeInputSpec);
    //Read
    FindNoticeOutputSpec getNoticeById(Long noticeId);
    List<FindListNoticeOutputSpec> getNoticeList(FindListNoticeInputSpec findListNoticeInputSpec);
    //Update
    void editNotice(EditNoticeInputSpec editNoticeForm);
    //Delete
    void deleteNoticeById(Long noticeId);

}
