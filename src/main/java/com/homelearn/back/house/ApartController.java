package com.homelearn.back.house;

import com.homelearn.back.common.util.MessageUtil;
import com.homelearn.back.house.dto.*;
import com.homelearn.back.house.entity.HouseJoinLike;
import com.homelearn.back.news.CrawlerToObjectMaker;
import com.homelearn.back.user.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/apart")
@RequiredArgsConstructor
@Slf4j
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
                                .map(m->{
                                    if (m.getAptImg()==null){
                                        log.error("naverAPI 실행 = {}",m.getAptImg() );
                                        return new ApartOutputSpec().houseJoinLikeToApartOutputSpec(m,maker.getImg(m));
                                    }else {
                                        return new ApartOutputSpec().houseJoinLikeToApartOutputSpec(m);
                                    }
                                })
                                .collect(Collectors.toList())));
    }

    @GetMapping("/find/{apartCode}")
    public ResponseEntity<MessageUtil<ApartOutputSpec>> findApt(
            @PathVariable("apartCode") Long apartCode,
            @AuthenticationPrincipal User user
    ){
        HouseJoinLike apart = apartService.getApartInfoById(apartCode, user);
        if (apart.getAptImg()!=null){
            return ResponseEntity.ok().body(MessageUtil.success(
                            new ApartOutputSpec().houseJoinLikeToApartOutputSpec(apart)));
        }
        return ResponseEntity.ok().body(MessageUtil.success(
                new ApartOutputSpec().houseJoinLikeToApartOutputSpec(apart, maker.getImg(apart))));
    }

    @GetMapping("/history")
    public ResponseEntity<MessageUtil<List<DealListOutputSpec>>> findAptDeal(
            @ModelAttribute DealListInputSpec inputSpec
            ){
        return ResponseEntity.ok().body(
                MessageUtil.success(
                        apartService.getApartDealList(inputSpec))
                );

    }

}
