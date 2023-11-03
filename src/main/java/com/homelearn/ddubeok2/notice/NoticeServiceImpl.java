package com.homelearn.ddubeok2.notice;

import com.homelearn.ddubeok2.notice.dto.NoticeForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NoticeServiceImpl implements NoticeService{

    private final NoticeMapper noticeMapper;


    @Override
    public void addNotice(NoticeForm noticeForm) {
        noticeMapper.addNotice(noticeForm);
    }

    @Override
    public Notice getNoticeById(Long noticeId) {
        return noticeMapper.getNoticeById(noticeId);
    }

    @Override
    public List<Notice> getNoticeList() {
        return noticeMapper.getNoticeList();
    }

    @Override
    public void editNotice(NoticeForm noticeForm) {
        noticeMapper.editNotice(noticeForm);
    }

    @Override
    public void countNotice(Long noticeId) {
        noticeMapper.countNotice(noticeId);
    }

    @Override
    public void deleteNoticeById(Long noticeId) {
        noticeMapper.deleteNoticeById(noticeId);
    }
}
