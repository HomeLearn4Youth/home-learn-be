package com.homelearn.back.notice;

import com.homelearn.back.notice.dto.*;
import com.homelearn.back.notice.entity.Notice;
import com.homelearn.back.notice.entity.NoticeJoinMember;
import com.homelearn.back.notice.exception.NoticeErrorCode;
import com.homelearn.back.notice.exception.NoticeException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.homelearn.back.notice.exception.NoticeErrorCode.*;

@Service
@RequiredArgsConstructor
public class NoticeServiceImpl implements NoticeService{

    private final NoticeMapper noticeMapper;


    @Override
    public void addNotice(NoticeParam param, Long loginUserId) {
        //추후 권한 인증 들어가야함
        noticeMapper.addNotice(param);
    }

    @Override
    public NoticeJoinMember getNoticeById(Long noticeId) {
        NoticeJoinMember noticeJoinMember = noticeMapper
                .getNoticeById(noticeId)
                .orElseThrow(() -> new NoticeException(NOT_EXISTS_NOTICE));
        noticeMapper.countNotice(noticeId);
        return noticeJoinMember;
    }

    @Override
    public List<Notice> getNoticeList(FindListNoticeInputSpec findListNoticeInputSpec) {
        return noticeMapper.getNoticeList(findListNoticeInputSpec);
    }

    @Override
    public void editNotice(NoticeParam param, Long loginUserId) {
        if (noticeMapper
                .getNoticeById(param.getId())
                .orElseThrow(() -> new NoticeException(NOT_EXISTS_NOTICE))
                .getWriterId()!=loginUserId) throw new NoticeException(FORBIDDEN_NOTICE);
        noticeMapper.editNotice(param);
    }

    @Override
    public void deleteNoticeById(Long noticeId, Long loginUserId) {
        if (noticeMapper
                .getNoticeById(noticeId)
                .orElseThrow(() -> new NoticeException(NOT_EXISTS_NOTICE))
                .getWriterId()!=loginUserId) throw new NoticeException(FORBIDDEN_NOTICE);
        noticeMapper.deleteNoticeById(noticeId);
    }
}
