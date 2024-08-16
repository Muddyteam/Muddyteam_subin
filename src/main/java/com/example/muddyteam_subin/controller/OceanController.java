package com.example.muddyteam_subin.controller;

import com.example.muddyteam_subin.service.OceanService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    private final OceanService oceanService;

    @GetMapping("/tide")
    public void getTide(){
        oceanService.getTide();
    }

}
