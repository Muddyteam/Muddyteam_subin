package com.example.muddyteam_subin.controller;

import com.example.muddyteam_subin.dto.controller.ocean.day.response.OceanTideTheDayResponseDto;
import com.example.muddyteam_subin.dto.controller.ocean.seven.response.OceanTideSevenDaysResponseDto;
import com.example.muddyteam_subin.service.OceanGetTideSevenDaysService;
import com.example.muddyteam_subin.service.OceanGetTideTheDayService;
import com.example.muddyteam_subin.service.OceanService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/ocean")
@RequiredArgsConstructor
public class OceanController {

    /*
    * 1. 7일치의 물때표가 필요
    * 2.
    *
    * 1. 00시마다 7일치의 물때표 정보를 가져옴
    * 2. DB에 저장 후 가져옴
    * */

    private final OceanGetTideSevenDaysService oceanGetTideSevenDaysService;
    private final OceanGetTideTheDayService oceanGetTideTheDayService;

    @GetMapping("/tide/{oceanName}")
    public ResponseEntity<OceanTideSevenDaysResponseDto> getTideSevenDays(@PathVariable("oceanName") String oceanName){
        log.info("oceanName : {}", oceanName);
        return ResponseEntity.ok(oceanGetTideSevenDaysService.getTideSevenDays(oceanName));
    }

    @GetMapping("/tide/{oceanName}/{day}")
    public ResponseEntity<OceanTideTheDayResponseDto> getTideTheDay(@PathVariable("oceanName") String oceanName, @PathVariable("day") String day){
        log.info("oceanName : {}", oceanName);
        log.info("day : {}", day);
        return ResponseEntity.ok(oceanGetTideTheDayService.getTideTheDay(oceanName, day));
    }

}
