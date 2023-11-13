package com.homelearn.back.dong;

import com.homelearn.back.dong.dto.Dong;
import com.homelearn.back.dong.dto.Gugun;
import com.homelearn.back.dong.dto.Sido;
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
    public ResponseEntity<List<Sido>> getSidoList(){
        return ResponseEntity.ok().body(dongService.getSidoList());
    }

    @GetMapping("/gugun_list")
    public ResponseEntity<List<Gugun>> getGugunList(@RequestParam("code") String code){
        return ResponseEntity.ok().body(dongService.getGugunList(code));
    }
    @GetMapping("/dong_list")
    public ResponseEntity<List<Dong>> getDongList(@RequestParam("code") String code){
        return ResponseEntity.ok().body(dongService.getDongList(code));
    }
}
