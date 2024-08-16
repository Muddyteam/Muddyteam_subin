package com.example.muddyteam_subin.dto.controller.ocean.seven.response;

import com.example.muddyteam_subin.dto.controller.ocean.seven.OceanTideSevenDaysDto;
import java.util.List;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OceanTideSevenDaysResponseDto {

    List<OceanTideSevenDaysDto> oceanTideList;

}
