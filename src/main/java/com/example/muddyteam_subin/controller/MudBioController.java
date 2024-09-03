package com.example.muddyteam_subin.controller;

import com.example.muddyteam_subin.service.MudBioService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MudBioController {

    private final MudBioService mudBioService;

    public MudBioController(MudBioService mudBioService) {
        this.mudBioService = mudBioService;
    }

    @GetMapping("/mudbio/info")
    public String getMudBioInfo(
            // num:103까지 존재
            @RequestParam(value = "_type", defaultValue = "xml") String type,
            @RequestParam(value = "numOfRows", defaultValue = "103") String numOfRows,
            @RequestParam(value = "pageNo", defaultValue = "1") String pageNo) {

        return mudBioService.getMudBioInfo(type, numOfRows, pageNo);
    }
}
