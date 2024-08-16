package com.example.muddyteam_subin.dto.controller.ocean.day.response;

import com.example.muddyteam_subin.dto.controller.ocean.day.OceanTideTheDayDto;
import java.util.List;
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
public class OceanTideTheDayResponseDto {

    private List<OceanTideTheDayDto> oceanTideTheDayList;

}
