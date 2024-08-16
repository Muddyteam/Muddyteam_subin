package com.example.muddyteam_subin.dto.ocean.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OceanTideResponseDto {

    private int lowTideTime;
    private int lowTideLevel;
    private int highTideTime;
    private int highTideLevel;

}
