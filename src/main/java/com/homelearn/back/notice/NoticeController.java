package com.homelearn.back.notice;

import com.homelearn.back.notice.dto.*;
import com.homelearn.back.notice.entity.Notice;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<FindNoticeOutputSpec> findNotice(
            @PathVariable("noticeId") Long noticeId
    ){
        return ResponseEntity.ok().body(noticeService.getNoticeById(noticeId));
    }

    /**
     * 공지사항 전체 조회
     * @return
     */
    @GetMapping("/findlist")
    public ResponseEntity<List<FindListNoticeOutputSpec>> findNoticeList(
            @RequestBody FindListNoticeInputSpec findListNoticeInputSpec
            ){
        return ResponseEntity.ok().body(noticeService.getNoticeList(findListNoticeInputSpec));
    }

    /**
     *
     * @param editNoticeInputSpec
     * @return
     */
    @PutMapping("/edit")
    public ResponseEntity editNotice(
            @RequestBody EditNoticeInputSpec editNoticeInputSpec
            ){
        noticeService.editNotice(editNoticeInputSpec);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/add")
    public ResponseEntity addNotice(
            @RequestBody AddNoticeInputSpec addNoticeInputSpec
            ){
        System.out.println(addNoticeInputSpec.getContent());
        System.out.println(addNoticeInputSpec.getTitle());
        System.out.println(addNoticeInputSpec.getWriterId());
        noticeService.addNotice(addNoticeInputSpec);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/{noticeId}")
    public ResponseEntity deleteNotice(
            @PathVariable("noticeId") Long noticeId
    ){
        noticeService.deleteNoticeById(noticeId);
        return ResponseEntity.ok().build();
    }


}
