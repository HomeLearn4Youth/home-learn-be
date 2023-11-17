package com.homelearn.back.dong;

import com.homelearn.back.common.util.MessageUtil;
import com.homelearn.back.dong.dto.Dong;
import com.homelearn.back.dong.dto.Gugun;
import com.homelearn.back.dong.dto.Sido;
import com.nimbusds.oauth2.sdk.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dong")
@RequiredArgsConstructor
public class DongController {
    private final DongService dongService;

    @GetMapping("/sido_list")
    public ResponseEntity<MessageUtil<List<Sido>>> getSidoList(){
        return ResponseEntity.ok().body(MessageUtil.success(dongService.getSidoList()));
    }

    @GetMapping("/gugun_list/{code}")
    public ResponseEntity<MessageUtil<List<Gugun>>> getGugunList(
            @PathVariable("code") String code){
        return ResponseEntity.ok().body(MessageUtil.success(dongService.getGugunList(code)));
    }

    @GetMapping("/dong_list/{code}")
    public ResponseEntity<MessageUtil<List<Dong>>> getDongList(
            @PathVariable("code") String code){
        return ResponseEntity.ok().body(MessageUtil.success(dongService.getDongList(code)));
    }
}
