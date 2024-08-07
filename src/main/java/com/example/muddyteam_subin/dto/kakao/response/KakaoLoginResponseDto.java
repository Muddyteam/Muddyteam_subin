package com.example.muddyteam_subin.dto.kakao.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class KakaoLoginResponseDto {

    private String username;
    private String accessToken;
    private String refreshToken;

}
