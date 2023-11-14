package com.homelearn.back.notice;

import com.homelearn.back.notice.dto.*;
import com.homelearn.back.notice.entity.Notice;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NoticeServiceImpl implements NoticeService{

    private final NoticeMapper noticeMapper;


    @Override
    public void addNotice(AddNoticeInputSpec addNoticeInputSpec) {

    }

    @Override
    public FindNoticeOutputSpec getNoticeById(Long noticeId) {
        return null;
    }

    @Override
    public List<FindListNoticeOutputSpec> getNoticeList(FindListNoticeInputSpec findListNoticeInputSpec) {
        return null;
    }

    @Override
    public void editNotice(EditNoticeInputSpec editNoticeForm) {

    }

    @Override
    public void deleteNoticeById(Long noticeId) {

    }
}
