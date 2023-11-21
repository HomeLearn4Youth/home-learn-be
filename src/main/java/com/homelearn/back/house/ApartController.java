package com.homelearn.back.house;

import com.homelearn.back.common.util.MessageUtil;
import com.homelearn.back.house.dto.*;
import com.homelearn.back.news.CrawlerToObjectMaker;
import com.homelearn.back.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/apart")
@RequiredArgsConstructor
public class ApartController {
    private final ApartService apartService;
    private final CrawlerToObjectMaker maker;
    /**
     * sec 적용 이후 @AuthenticationPrincipal LoginUser 를 통해 user 정보를 가져와서 넣어야함
     * 현재는 userId를 PathVariable에서 가져오는 방식
     */
    @GetMapping("/findlist")
    public ResponseEntity<MessageUtil<List<ApartOutputSpec>>> findAptList(
            @ModelAttribute ApartListInputSpec inputSpec,
            @AuthenticationPrincipal User user
            ){
        return ResponseEntity.ok().body(
                MessageUtil.success(
                        apartService.getApartList(inputSpec, user).stream()
                                .map(m->new ApartOutputSpec().houseJoinLikeToApartOutputSpec(m,maker.getImg(m)))
                                .collect(Collectors.toList())));
    }

    @GetMapping("/find/{apartCode}")
    public ResponseEntity<MessageUtil<ApartOutputSpec>> findApt(
            @PathVariable("apartCode") Long apartCode,
            @AuthenticationPrincipal User user
    ){
        return ResponseEntity.ok().body(
                MessageUtil.success(apartService.getApartInfoById(apartCode, user)));
    }

    @GetMapping("/history")
    public ResponseEntity<MessageUtil<List<DealListOutputSpec>>> findAptDeal(
            @ModelAttribute DealListInputSpec inputSpec
            ){
        System.out.println(inputSpec.toString());
        return ResponseEntity.ok().body(
                MessageUtil.success(
                        apartService.getApartDealList(inputSpec))
                );

    }

}
