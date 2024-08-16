package com.example.muddyteam_subin.dto.ocean.api.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TideApiRequestDto {

    private int date;
    private String ServiceKey;
    private String ObsCode;
    private String resultType;

}
