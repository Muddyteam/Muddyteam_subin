package com.example.muddyteam_subin.dto.controller.kakao.request;

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
public class KakaoLoginRequestDto {
    private Long id;
    private String nickname;
    private String profileImage;
    private String thumbnailImage;
}
