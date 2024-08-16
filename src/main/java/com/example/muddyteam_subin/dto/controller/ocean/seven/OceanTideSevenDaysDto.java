package com.example.muddyteam_subin.dto.controller.ocean.seven;


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
public class OceanTideSevenDaysDto {

    private Long oceanId;
    private Long oceanDayId;
    private Long oceanTideId;
    private String oceanName;
    private String oceanCode;

    private String day;

    private String hlCode;
    private String tphTime;
    private String tphLevel;

}
