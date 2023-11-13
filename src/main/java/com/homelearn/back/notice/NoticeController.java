package com.homelearn.back.notice;

import com.homelearn.back.notice.dto.NoticeForm;
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
    public ResponseEntity<Notice> findNotice(
            @PathVariable("noticeId") Long noticeId
    ){
        noticeService.countNotice(noticeId);
        return ResponseEntity.ok().body(noticeService.getNoticeById(noticeId));
    }

    /**
     * 공지사항 전체 조회
     * @return
     */
    @GetMapping("/findlist")
    public ResponseEntity<List<Notice>> findNoticeList(

    ){
        return ResponseEntity.ok().body(noticeService.getNoticeList());
    }

    /**
     *
     * @param noticeForm
     * @return
     */
    @PutMapping("/edit")
    public ResponseEntity<?> editNotice(
            @RequestBody NoticeForm noticeForm
            ){
        noticeService.editNotice(noticeForm);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/add")
    public ResponseEntity<?> addNotice(
            @RequestBody NoticeForm noticeForm
    ){
        noticeService.addNotice(noticeForm);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/{noticeId}")
    public ResponseEntity<?> deleteNotice(
            @PathVariable("noticeId") Long noticeId
    ){
        noticeService.deleteNoticeById(noticeId);
        return ResponseEntity.ok().build();
    }
}
