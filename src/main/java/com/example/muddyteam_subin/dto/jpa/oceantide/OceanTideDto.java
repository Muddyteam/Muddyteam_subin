package com.example.muddyteam_subin.dto.jpa.oceantide;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OceanTideDto {

    private Long oceanTideId;
    private Long oceanDayId;
    private String hlCode;
    private String tphTime;
    private String tphLevel;
    private int version;

}
