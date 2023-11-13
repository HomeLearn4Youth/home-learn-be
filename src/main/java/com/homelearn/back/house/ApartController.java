package com.homelearn.back.house;

import com.homelearn.back.house.dto.ApartDealOutput;
import com.homelearn.back.house.dto.ApartInfoOutput;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/apart")
@RequiredArgsConstructor
public class ApartController {
    private final ApartService apartService;

    @GetMapping("/findlist/{dongCode}")
    public ResponseEntity<List<ApartInfoOutput>> findAptList(
            @PathVariable("dongCode") Long dongCode
    ){
        return ResponseEntity.ok().body(apartService.getApartListByDongCode(dongCode));
    }

    @GetMapping("/find/{apartCode}")
    public ResponseEntity<ApartInfoOutput> findApt(
            @PathVariable("apartCode") Long apartCode
    ){
        return ResponseEntity.ok().body(apartService.getApartInfoById(apartCode));
    }

    @GetMapping("/history/{apartCode}")
    public ResponseEntity<List<ApartDealOutput>> findAptDeal(
            @PathVariable("apartCode") Long apartCode
    ){
        return ResponseEntity.ok().body(apartService.getApartDealById(apartCode));
    }
}
