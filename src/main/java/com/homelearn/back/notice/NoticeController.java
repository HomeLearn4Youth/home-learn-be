package com.homelearn.back.notice;

import com.homelearn.back.common.util.MessageUtil;
import com.homelearn.back.notice.dto.*;
import com.homelearn.back.notice.entity.Notice;
import com.homelearn.back.notice.entity.NoticeJoinMember;
import com.homelearn.back.notice.exception.NoticeErrorCode;
import com.homelearn.back.notice.exception.NoticeException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
    @GetMapping("/find/{noticeId}/{userId}")
    public ResponseEntity<MessageUtil<FindNoticeOutputSpec>> findNotice(
            @PathVariable("noticeId") Long noticeId,
            @PathVariable("userId") Long userId
    ){
        return ResponseEntity.ok().body(
                MessageUtil.success(
                        new FindNoticeOutputSpec().noticeJoinMemberToFindOutputSpec(
                                noticeService.getNoticeById(noticeId)
                        )));
    }

    /**
     * 공지사항 전체 조회
     * @return
     */
    @GetMapping("/findlist")
    public ResponseEntity<MessageUtil<List<FindListNoticeOutputSpec>>> findNoticeList(
            @ModelAttribute FindListNoticeInputSpec findListNoticeInputSpec
            ){
        return ResponseEntity.ok().body(
                MessageUtil.success(
                        noticeService.getNoticeList(findListNoticeInputSpec)
                            .stream()
                                .map(
                                    m -> new FindListNoticeOutputSpec()
                                            .noticeToFindListOutputSpec(m))
                            .collect(Collectors.toList()
                            )));
    }

    /**
     *
     * @param editNoticeInputSpec
     * @return
     */
    @PutMapping("/edit/{userId}")
    public ResponseEntity<MessageUtil> editNotice(
            @RequestBody EditNoticeInputSpec editNoticeInputSpec,
            @PathVariable("userId") Long userId
            ){

        noticeService.editNotice(NoticeParam.builder()
                        .id(editNoticeInputSpec.getNoticeId())
                        .content(editNoticeInputSpec.getContent())
                        .title(editNoticeInputSpec.getTitle())
                        .writerId(userId)
                        .build(), userId);
        return ResponseEntity.ok().body(MessageUtil.success());
    }

    @PostMapping("/add/{userId}")
    public ResponseEntity<MessageUtil> addNotice(
            @RequestBody AddNoticeInputSpec addNoticeInputSpec,
            @PathVariable("userId") Long userId
            ){
        //유저 권한 검증해야함  FORBIDDEN_NOTICE 날려줘야함

        noticeService.addNotice(NoticeParam.builder()
                .title(addNoticeInputSpec.getTitle())
                .content(addNoticeInputSpec.getContent())
                .writerId(userId)
                .build(), userId);
        return ResponseEntity.ok().body(MessageUtil.success());
    }

    @DeleteMapping("/delete/{noticeId}/{userId}")
    public ResponseEntity<MessageUtil> deleteNotice(
            @PathVariable("noticeId") Long noticeId,
            @PathVariable("userId") Long userId

    ){
        if (userId!=noticeService.getNoticeById(noticeId).getWriterId()){
            throw new NoticeException(FORBIDDEN_NOTICE);
        }
        noticeService.deleteNoticeById(noticeId, userId);
        return ResponseEntity.ok().body(MessageUtil.success());
    }


}
