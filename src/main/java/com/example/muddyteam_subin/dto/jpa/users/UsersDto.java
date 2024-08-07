package com.example.muddyteam_subin.dto.jpa.users;

import com.example.muddyteam_subin.dto.jpa.usersessions.UserSessionsDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsersDto {
    private Long userId;
    private String username;
    private String nickname;
    private String profileImage;
    private String thumbnailImage;
    private String oneLiner;
    private String role;
    private String provider;
    private Long providerId;
    private UserSessionsDto userSessionsDto;
    private int version;
}
