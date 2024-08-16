package com.example.muddyteam_subin.service;

import com.example.muddyteam_subin.dto.jpa.oceanday.OceanDayDto;
import com.example.muddyteam_subin.dto.jpa.oceans.OceansDto;
import com.example.muddyteam_subin.dto.jpa.oceantide.OceanTideDto;
import com.example.muddyteam_subin.dto.controller.ocean.api.request.TideApiRequestDto;
import com.example.muddyteam_subin.dto.controller.ocean.api.response.DataDto;
import com.example.muddyteam_subin.dto.controller.ocean.api.response.TideApiResponseDto;
import com.example.muddyteam_subin.service.oceanday.persistence.OceanDayService;
import com.example.muddyteam_subin.service.oceans.persistence.OceansService;
import com.example.muddyteam_subin.service.oceantide.persistence.OceanTideService;
import java.util.ArrayList;
import java.util.Collections;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@Slf4j
public class OceanService {

    @Value("${ocean.tide.api}")
    private String apiKey;

    private final OceansService oceansService;
    private final OceanDayService oceanDayService;
    private final OceanTideService oceanTideService;
    private final RestTemplate restTemplate;

    public OceanService(OceansService oceansService, OceanDayService oceanDayService,
        OceanTideService oceanTideService, RestTemplateBuilder restTemplateBuilder) {
        this.oceansService = oceansService;
        this.oceanDayService = oceanDayService;
        this.oceanTideService = oceanTideService;
        this.restTemplate = restTemplateBuilder.build();
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
        messageConverters.add(converter);
        this.restTemplate.setMessageConverters(messageConverters);
    }

    @Scheduled(cron = "0 0 0 * * MON")
    public void getTide() {

        List<OceansDto> oceansDtos = oceansService.findAll();

        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        int todayDate = Integer.parseInt(today.format(formatter));

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
                        + requestDto.getDate() + "&ResultType=" + requestDto.getResultType();

                log.info("url : {}", url);

                try {
                    ResponseEntity<TideApiResponseDto> response = restTemplate.getForEntity(url, TideApiResponseDto.class);

                    if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                        TideApiResponseDto tideApiResponseDto = response.getBody();

                        log.info("관측소 ID: {}", tideApiResponseDto.getResultDto().getMetaDto().getObsPostId());
                        log.info("관측소 이름: {}", tideApiResponseDto.getResultDto().getMetaDto().getObsPostName());
                        log.info("위도: {}", tideApiResponseDto.getResultDto().getMetaDto().getObsLat());
                        log.info("경도: {}", tideApiResponseDto.getResultDto().getMetaDto().getObsLon());
                        log.info("요청 횟수: {}", tideApiResponseDto.getResultDto().getMetaDto().getObsLastReqCnt());


                        OceanDayDto oceanDayDto = oceanDayService.findByOceanIdAndDayOrElseNew(obs.getOceanId(), String.valueOf(requestDto.getDate()));
                        oceanDayDto.setOceanId(obs.getOceanId());
                        oceanDayDto.setDay(String.valueOf(requestDto.getDate()));

                        OceanDayDto savedOceanDaydto = oceanDayService.save(oceanDayDto);

                        for(DataDto dataDto : response.getBody().getResultDto().getDataDtoList()){
                            OceanTideDto oceanTideDto = oceanTideService.findByOceanDayIdAndTphTime(savedOceanDaydto.getOceanDayId(), dataDto.getTphTime());
                            oceanTideDto.setOceanDayId(savedOceanDaydto.getOceanDayId());
                            oceanTideDto.setHlCode(dataDto.getHlCode());
                            oceanTideDto.setTphTime(dataDto.getTphTime());
                            oceanTideDto.setTphLevel(dataDto.getTphLevel());

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