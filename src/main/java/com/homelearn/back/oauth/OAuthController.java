package com.homelearn.back.oauth;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/oauth")
public class OAuthController {
//    @GetMapping("/callback/naver")
//    public String naverOauthRedirect(@RequestParam String code) {
//        return "네이버 로그인 인증 완료, code : " + code;
//    }
}
