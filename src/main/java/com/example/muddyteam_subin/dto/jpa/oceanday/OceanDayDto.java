package com.example.muddyteam_subin.dto.jpa.oceanday;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OceanDayDto {

    private Long oceanDayId;
    private Long oceanId;
    private String day;
    private int version;

}
