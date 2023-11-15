package com.homelearn.back.house;

import com.homelearn.back.house.dto.*;
import com.homelearn.back.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/apart")
@RequiredArgsConstructor
public class ApartController {
    private final ApartService apartService;

    /**
     * sec 적용 이후 @AuthenticationPrincipal LoginUser 를 통해 user 정보를 가져와서 넣어야함
     * 현재는 userId를 PathVariable에서 가져오는 방식
     */
    @GetMapping("/findlist/{userId}")
    public ResponseEntity<List<ApartOutputSpec>> findAptList(
            @ModelAttribute ApartListInputSpec inputSpec,
            @PathVariable("userId") Long userId
            ){
        return ResponseEntity.ok().body(
                apartService.getApartList(
                        new ApartListParam()
                                .apartListInputSpecToApartListParam(inputSpec, userId)
                ));
    }

    @GetMapping("/find/{apartCode}/{userId}")
    public ResponseEntity<ApartOutputSpec> findApt(
            @PathVariable("apartCode") Long apartCode,
            @PathVariable("userId") Long userId
    ){
        return ResponseEntity.ok().body(
                apartService.getApartInfoById(
                    ApartInfoParam.builder()
                            .aptCode(apartCode)
                            .userId(userId)
                            .build()
                ));
    }

    @GetMapping("/history")
    public ResponseEntity<List<DealListOutputSpec>> findAptDeal(
            @ModelAttribute DealListInputSpec inputSpec
            ){
        System.out.println(inputSpec.toString());
        return ResponseEntity.ok().body(apartService.getApartDealList(inputSpec));
    }
}
