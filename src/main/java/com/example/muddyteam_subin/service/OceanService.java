package com.example.muddyteam_subin.service;

import com.example.muddyteam_subin.dto.jpa.oceanday.OceanDayDto;
import com.example.muddyteam_subin.dto.jpa.oceans.OceansDto;
import com.example.muddyteam_subin.dto.jpa.oceantide.OceanTideDto;
import com.example.muddyteam_subin.dto.ocean.api.request.TideApiRequestDto;
import com.example.muddyteam_subin.dto.ocean.api.response.TideApiResponseDto;
import com.example.muddyteam_subin.service.oceanday.persistence.OceanDayService;
import com.example.muddyteam_subin.service.oceans.persistence.OceansService;
import com.example.muddyteam_subin.service.oceantide.persistence.OceanTideService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class OceanService {

    @Value("${ocean.tide.api}")
    private String apiKey;

    private final OceansService oceansService;
    private final OceanDayService oceanDayService;
    private final OceanTideService oceanTideService;

    public void getTide() {

        List<OceansDto> oceansDtos = oceansService.findAll();

        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        int todayDate = Integer.parseInt(today.format(formatter));

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");

        HttpEntity<String> entity = new HttpEntity<>(headers);
        for(OceansDto obs : oceansDtos) {
            for (int i = 0; i < 7; i++) {
                TideApiRequestDto requestDto = TideApiRequestDto.builder()
                        .date(todayDate + i)
                        .ObsCode(obs.getOceanCode())
                        .ServiceKey(apiKey)
                        .resultType("json")
                        .build();
                String url = "https://www.khoa.go.kr/api/oceangrid/tideObsPreTab/search.do?ServiceKey="
                        + requestDto.getServiceKey() + "&ObsCode=" + requestDto.getObsCode() + "&Date="
                        + requestDto.getDate() + "&ResultType=json";

                try {
                    ResponseEntity<TideApiResponseDto> response = restTemplate.getForEntity(url, TideApiResponseDto.class);
                    if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                        TideApiResponseDto tideApiResponseDto = response.getBody();



                        System.out.println("관측소 ID: " + tideApiResponseDto.getResultDto().getMetaDto().getObsPostId());
                        System.out.println("관측소 이름: " + tideApiResponseDto.getResultDto().getMetaDto().getObsPostName());
                        System.out.println("위도: " + tideApiResponseDto.getResultDto().getMetaDto().getObsLat());
                        System.out.println("경도: " + tideApiResponseDto.getResultDto().getMetaDto().getObsLon());
                        System.out.println("요청 횟수: " + tideApiResponseDto.getResultDto().getMetaDto().getObsLastReqCnt());

                        OceanDayDto oceanDayDto = OceanDayDto.builder()
                                .day(String.valueOf(requestDto.getDate()))
                                .oceanId(obs.getOceanId())
                                .build();
                        OceanDayDto savedOceanDaydto = oceanDayService.save(oceanDayDto);

                        for(TideApiResponseDto.ResultDto.DataDto dataDto : response.getBody().getResultDto().getDataDtos()){
                            OceanTideDto oceanTideDto = OceanTideDto.builder()
                                    .oceanDayId(savedOceanDaydto.getOceanId())
                                    .hlCode(dataDto.getHlCode())
                                    .tphTime(dataDto.getTphTime())
                                    .tphLevel(dataDto.getTphLevel())
                                    .build();
                            OceanTideDto savedOceanTideDto = oceanTideService.save(oceanTideDto);

                            log.info(savedOceanTideDto.getHlCode());
                            log.info(savedOceanTideDto.getTphTime());
                            log.info(savedOceanTideDto.getTphLevel());
                        }
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}