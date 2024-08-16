package com.example.muddyteam_subin.service;

import com.example.muddyteam_subin.common.property.JwtProperty;
import com.example.muddyteam_subin.dto.jpa.users.UsersDto;
import com.example.muddyteam_subin.dto.jpa.usersessions.UserSessionsDto;
import com.example.muddyteam_subin.dto.controller.kakao.request.KakaoLoginRequestDto;
import com.example.muddyteam_subin.dto.controller.kakao.response.KakaoLoginResponseDto;
import com.example.muddyteam_subin.security.JwtUtil;
import com.example.muddyteam_subin.service.users.persist.UsersService;
import com.example.muddyteam_subin.service.usersessions.persist.UserSessionsService;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KakaoService {

    private final UsersService usersService;
    private final UserSessionsService userSessionsService;
    private final JwtUtil jwtUtil;
    private final JwtProperty jwtProperty;

    public ResponseEntity<KakaoLoginResponseDto> login(KakaoLoginRequestDto kakaoLoginRequestDto){

        UsersDto usersDto = UsersDto.builder()
            .username("kakao_" + kakaoLoginRequestDto.getId())
            .nickname(kakaoLoginRequestDto.getNickname())
            .profileImage(kakaoLoginRequestDto.getProfileImage())
            .thumbnailImage(kakaoLoginRequestDto.getThumbnailImage())
            .oneLiner(null)
            .role("ROLE_USER")
            .provider("kakao")
            .providerId(kakaoLoginRequestDto.getId())
            .build();

        UsersDto member = usersService.existsByUsername(usersDto);

        Long userId = member.getUserId();
        String role = member.getRole();
        String accessToken = jwtUtil.createAccessToken(userId, role);
        String refreshToken = jwtUtil.createRefreshToken(userId, role);

        UserSessionsDto userSessionsDto = userSessionsService.existsByuserId(userId);
        userSessionsDto.setUserId(userId);
        userSessionsDto.setAccessToken(accessToken);
        userSessionsDto.setRefreshToken(refreshToken);
        userSessionsDto.setExpiration(LocalDateTime.now().plusSeconds(jwtProperty.getAccess().getExpiration() / 1000));

        userSessionsService.save(userSessionsDto);

        KakaoLoginResponseDto kakaoLoginResponseDto = KakaoLoginResponseDto.builder()
            .username(member.getUsername())
            .accessToken(accessToken)
            .refreshToken(refreshToken)
            .build();

        return ResponseEntity.ok(kakaoLoginResponseDto);
    }

}
