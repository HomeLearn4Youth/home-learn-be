package com.homelearn.back.notice;

import com.homelearn.back.common.util.MessageUtil;
import com.homelearn.back.notice.dto.*;
import com.homelearn.back.notice.entity.Notice;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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
                            .stream().map(
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
    @PutMapping("/edit")
    public ResponseEntity<MessageUtil> editNotice(
            @RequestBody EditNoticeInputSpec editNoticeInputSpec
            ){
        noticeService.editNotice(editNoticeInputSpec);
        return ResponseEntity.ok().body(MessageUtil.success());
    }

    @PostMapping("/add")
    public ResponseEntity<MessageUtil> addNotice(
            @RequestBody AddNoticeInputSpec addNoticeInputSpec
            ){
        noticeService.addNotice(addNoticeInputSpec);
        return ResponseEntity.ok().body(MessageUtil.success());
    }

    @DeleteMapping("/delete/{noticeId}")
    public ResponseEntity<MessageUtil> deleteNotice(
            @PathVariable("noticeId") Long noticeId
    ){
        noticeService.deleteNoticeById(noticeId);
        return ResponseEntity.ok().body(MessageUtil.success());
    }


}
