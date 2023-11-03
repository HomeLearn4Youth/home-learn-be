package com.homelearn.ddubeok2.notice;

import com.homelearn.ddubeok2.notice.dto.NoticeForm;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notice")
@RequiredArgsConstructor
public class NoticeController {

    private final NoticeService noticeService;

    @GetMapping("/{noticeId}")
    public ResponseEntity<Notice> viewNoticeDetail(
            @PathVariable("noticeId") Long noticeId
    ){
        noticeService.countNotice(noticeId);
        Notice notice = noticeService.getNoticeById(noticeId);
        return ResponseEntity.ok().body(notice);
    }

    @GetMapping("/list")
    public ResponseEntity<List<Notice>> viewList(

    ){
        return ResponseEntity.ok().body(noticeService.getNoticeList());
    }

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

    @DeleteMapping("/{noticeId}/delete")
    public ResponseEntity<?> deleteNotice(
            @PathVariable("noticeId") Long noticeId
    ){
        noticeService.deleteNoticeById(noticeId);
        return ResponseEntity.ok().build();
    }
}
