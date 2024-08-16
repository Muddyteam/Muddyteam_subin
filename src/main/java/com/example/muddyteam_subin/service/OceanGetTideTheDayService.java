package com.example.muddyteam_subin.service;

import com.example.muddyteam_subin.dto.controller.ocean.day.OceanTideTheDayDto;
import com.example.muddyteam_subin.dto.controller.ocean.day.response.OceanTideTheDayResponseDto;
import com.example.muddyteam_subin.dto.controller.ocean.seven.OceanTideSevenDaysDto;
import com.example.muddyteam_subin.dto.jpa.oceanday.OceanDayDto;
import com.example.muddyteam_subin.dto.jpa.oceans.OceansDto;
import com.example.muddyteam_subin.dto.jpa.oceantide.OceanTideDto;
import com.example.muddyteam_subin.service.oceanday.persistence.OceanDayService;
import com.example.muddyteam_subin.service.oceans.persistence.OceansService;
import com.example.muddyteam_subin.service.oceantide.persistence.OceanTideService;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class OceanGetTideTheDayService {

    private final OceansService oceansService;
    private final OceanDayService oceanDayService;
    private final OceanTideService oceanTideService;

    public OceanTideTheDayResponseDto getTideTheDay(String oceanName, String day){

        OceansDto oceansDto = oceansService.findByOceanName(oceanName);
        OceanDayDto oceanDayDto = oceanDayService.findByOceanIdAndDay(oceansDto.getOceanId(), day);
        List<OceanTideDto> oceanTideDtos = oceanTideService.findByOceanDayId(oceanDayDto.getOceanDayId());

        List<OceanTideTheDayDto> list = new ArrayList<>();

        for(OceanTideDto otd : oceanTideDtos){
            OceanTideTheDayDto oceanTideTheDayDto = OceanTideTheDayDto.builder()
                .oceanId(oceansDto.getOceanId())
                .oceanDayId(oceanDayDto.getOceanDayId())
                .oceanTideId(otd.getOceanTideId())
                .oceanName(oceansDto.getOceanName())
                .oceanCode(oceansDto.getOceanCode())
                .day(day)
                .hlCode(otd.getHlCode())
                .tphTime(otd.getTphTime())
                .tphLevel(otd.getTphLevel())
                .build();

            list.add(oceanTideTheDayDto);
        }

        return OceanTideTheDayResponseDto.builder()
            .oceanTideTheDayList(list)
            .build();
    }

}
