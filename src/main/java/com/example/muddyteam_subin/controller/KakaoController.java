package com.example.muddyteam_subin.controller;

import com.example.muddyteam_subin.dto.controller.kakao.request.KakaoLoginRequestDto;
import com.example.muddyteam_subin.dto.controller.kakao.response.KakaoLoginResponseDto;
import com.example.muddyteam_subin.service.KakaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/kakao")
@RequiredArgsConstructor
public class KakaoController {

    private final KakaoService kakaoService;

    @CrossOrigin
    @PostMapping("/login")
    public ResponseEntity<KakaoLoginResponseDto> login(@RequestBody final KakaoLoginRequestDto kakaoLoginRequestDto) {
        System.out.println(kakaoLoginRequestDto.getId());
        System.out.println(kakaoLoginRequestDto.getNickname());
        System.out.println(kakaoLoginRequestDto.getProfileImage());
        System.out.println(kakaoLoginRequestDto.getThumbnailImage());
        return kakaoService.login(kakaoLoginRequestDto);
    }
}
