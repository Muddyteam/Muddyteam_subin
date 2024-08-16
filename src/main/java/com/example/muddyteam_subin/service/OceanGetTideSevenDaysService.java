package com.example.muddyteam_subin.service;

import com.example.muddyteam_subin.dto.controller.ocean.seven.OceanTideSevenDaysDto;
import com.example.muddyteam_subin.dto.controller.ocean.seven.response.OceanTideSevenDaysResponseDto;
import com.example.muddyteam_subin.dto.jpa.oceanday.OceanDayDto;
import com.example.muddyteam_subin.dto.jpa.oceans.OceansDto;
import com.example.muddyteam_subin.dto.jpa.oceantide.OceanTideDto;
import com.example.muddyteam_subin.service.oceanday.persistence.OceanDayService;
import com.example.muddyteam_subin.service.oceans.persistence.OceansService;
import com.example.muddyteam_subin.service.oceantide.persistence.OceanTideService;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class OceanGetTideSevenDaysService {

    private final OceansService oceansService;
    private final OceanDayService oceanDayService;
    private final OceanTideService oceanTideService;

    public OceanTideSevenDaysResponseDto getTideSevenDays(String oceanName){

        OceansDto oceansDto = oceansService.findByOceanName(oceanName);

        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        int todayDate = Integer.parseInt(today.format(formatter));

        List<OceanTideSevenDaysDto> list = new ArrayList<>();

        for(int i = 0; i < 7; i++){
            String day = String.valueOf(todayDate + i);
            OceanDayDto oceanDayDto = oceanDayService.findByOceanIdAndDay(oceansDto.getOceanId(), day);
            List<OceanTideDto> oceanTideDtos = oceanTideService.findByOceanDayId(oceanDayDto.getOceanDayId());

            for(OceanTideDto otd : oceanTideDtos){
                OceanTideSevenDaysDto oceanTideSevenDaysDto = OceanTideSevenDaysDto.builder()
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

                list.add(oceanTideSevenDaysDto);
            }
        }

        return OceanTideSevenDaysResponseDto.builder()
            .oceanTideList(list)
            .build();
    }

}
