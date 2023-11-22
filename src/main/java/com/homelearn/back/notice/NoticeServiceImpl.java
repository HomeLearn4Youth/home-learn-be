package com.homelearn.back.notice;

import com.homelearn.back.notice.dto.*;
import com.homelearn.back.notice.entity.Notice;
import com.homelearn.back.notice.entity.NoticeJoinMember;
import com.homelearn.back.notice.exception.NoticeErrorCode;
import com.homelearn.back.notice.exception.NoticeException;
import com.homelearn.back.user.UserRole;
import com.homelearn.back.user.entity.User;
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
    public void addNotice(AddNoticeInputSpec inputSpec, User loginUser) {
        if (loginUser.getRole()!= UserRole.ADMIN) throw new NoticeException(FORBIDDEN_NOTICE);
        noticeMapper.addNotice(NoticeParam.builder()
                        .title(inputSpec.getTitle())
                        .content(inputSpec.getContent())
                        .writerId(loginUser.getId())
                        .build());
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
    public void editNotice(EditNoticeInputSpec inputSpec, User loginUser) {
        if (noticeMapper
                .getNoticeById(inputSpec.getNoticeId())
                .orElseThrow(() -> new NoticeException(NOT_EXISTS_NOTICE))
                .getWriterId()!= loginUser.getId()) throw new NoticeException(FORBIDDEN_NOTICE);
        noticeMapper.editNotice(NoticeParam.builder()
                .id(inputSpec.getNoticeId())
                .content(inputSpec.getContent())
                .title(inputSpec.getTitle())
                .writerId(loginUser.getId())
                .build());
    }

    @Override
    public void deleteNoticeById(Long noticeId, User loginUser) {
        if (noticeMapper
                .getNoticeById(noticeId)
                .orElseThrow(() -> new NoticeException(NOT_EXISTS_NOTICE))
                .getWriterId() != loginUser.getId()) throw new NoticeException(FORBIDDEN_NOTICE);
        noticeMapper.deleteNoticeById(noticeId);
    }

    @Override
    public Integer getTotalCount() {
        return noticeMapper.getTotalCount();
    }
}
