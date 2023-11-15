package com.homelearn.back.notice;

import com.homelearn.back.notice.dto.*;
import com.homelearn.back.notice.entity.Notice;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class NoticeServiceImpl implements NoticeService{

    private final NoticeMapper noticeMapper;


    @Override
    public void addNotice(AddNoticeInputSpec addNoticeInputSpec) {
        noticeMapper.addNotice(addNoticeInputSpec);
    }

    @Override
    public FindNoticeOutputSpec getNoticeById(Long noticeId) {
        noticeMapper.countNotice(noticeId);
        return new FindNoticeOutputSpec()
                .noticeJoinMemberToFindOutputSpec(noticeMapper.getNoticeById(noticeId));
    }

    @Override
    public List<FindListNoticeOutputSpec> getNoticeList(FindListNoticeInputSpec findListNoticeInputSpec) {
        return noticeMapper.getNoticeList(findListNoticeInputSpec).stream()
                .map(m -> new FindListNoticeOutputSpec()
                        .noticeToFindListOutputSpec(m))
                .collect(Collectors.toList());
    }

    @Override
    public void editNotice(EditNoticeInputSpec editNoticeForm) {
        noticeMapper.editNotice(editNoticeForm);
    }

    @Override
    public void deleteNoticeById(Long noticeId) {
        noticeMapper.deleteNoticeById(noticeId);
    }
}
