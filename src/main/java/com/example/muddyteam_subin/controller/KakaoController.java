package com.example.muddyteam_subin.controller;

import com.example.muddyteam_subin.dto.kakao.request.KakaoLoginRequestDto;
import com.example.muddyteam_subin.dto.kakao.response.KakaoLoginResponseDto;
import com.example.muddyteam_subin.service.KakaoService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kakao")
@RequiredArgsConstructor
public class KakaoController {

    private final KakaoService kakaoService;
    private final Logger LOGGER = LoggerFactory.getLogger(KakaoController.class);

    @PostMapping("/login")
    public ResponseEntity<KakaoLoginResponseDto> login(@RequestBody final KakaoLoginRequestDto kakaoLoginRequestDto){
        return kakaoService.login(kakaoLoginRequestDto);
    }

}
