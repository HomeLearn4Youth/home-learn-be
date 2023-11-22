package com.homelearn.back.notice;

import com.homelearn.back.common.util.MessageUtil;
import com.homelearn.back.notice.dto.*;
import com.homelearn.back.notice.entity.Notice;
import com.homelearn.back.notice.entity.NoticeJoinMember;
import com.homelearn.back.notice.exception.NoticeErrorCode;
import com.homelearn.back.notice.exception.NoticeException;
import com.homelearn.back.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.stream.Collectors;

import static com.homelearn.back.notice.exception.NoticeErrorCode.*;

@RestController
@RequestMapping("/notice")
@RequiredArgsConstructor
public class NoticeController {

    private final NoticeService noticeService;

    /**
     * 공지사항 조회
     * @param noticeId
     * @return
     */
    @GetMapping("/find/{noticeId}")
    public ResponseEntity<MessageUtil<FindNoticeOutputSpec>> findNotice(
            @PathVariable("noticeId") Long noticeId
    ){
        return ResponseEntity.ok().body(
                MessageUtil.success(
                        new FindNoticeOutputSpec()
                                .noticeJoinMemberToFindOutputSpec(
                                        noticeService.getNoticeById(noticeId))));
    }

    /**
     * 공지사항 전체 조회
     * @return
     */
    @GetMapping("/findlist")
    public ResponseEntity<MessageUtil<FindListNoticeOutputSpec>> findNoticeList(
            @ModelAttribute FindListNoticeInputSpec inputSpec
            ){
        return ResponseEntity.ok().body(
                MessageUtil.success(FindListNoticeOutputSpec.builder()
                                .items(noticeService.getNoticeList(inputSpec)
                                    .stream()
                                    .map(
                                        m -> new FindListNoticeItemOutputSpec()
                                                .noticeToFindListOutputSpec(m))
                                    .collect(Collectors.toList()))
                                .requestSearchText(inputSpec.getSearchText())
                                .requestStartIndex(inputSpec.getStartIndex())
                                .requestCount(inputSpec.getCount())
                                .totalCount(noticeService.getTotalCount())
                                .build()
                        ));
    }

    /**
     *
     * @param editNoticeInputSpec
     * @return
     */
    @PutMapping("/edit")
    public ResponseEntity<MessageUtil> editNotice(
            @RequestBody EditNoticeInputSpec editNoticeInputSpec,
            @AuthenticationPrincipal User user
            ){
        noticeService.editNotice(editNoticeInputSpec, user);
        return ResponseEntity.ok().body(MessageUtil.success());
    }

    @PostMapping("/add")
    public ResponseEntity<MessageUtil> addNotice(
            @RequestBody AddNoticeInputSpec addNoticeInputSpec,
            @AuthenticationPrincipal User user
            ){
        noticeService.addNotice(addNoticeInputSpec, user);
        return ResponseEntity.ok().body(MessageUtil.success());
    }

    @DeleteMapping("/delete/{noticeId}")
    public ResponseEntity<MessageUtil> deleteNotice(
            @PathVariable("noticeId") Long noticeId,
            @AuthenticationPrincipal User user

    ){
        noticeService.deleteNoticeById(noticeId, user);
        return ResponseEntity.ok().body(MessageUtil.success());
    }

}
