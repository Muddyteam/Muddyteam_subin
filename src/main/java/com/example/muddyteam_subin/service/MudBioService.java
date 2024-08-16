package com.example.muddyteam_subin.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class MudBioService {
    @Value("${api.serviceKey}")
    private String serviceKey;

    private final RestTemplate restTemplate;
    public String getMudBioInfo(String type, String numOfRows, String pageNo) {
        String url = "https://apis.data.go.kr/1192000/MudFlatInfoService/MudFlatEcoInfo"
                + "?_type=" + type
                + "&numOfRows=" + numOfRows
                + "&pageNo=" + pageNo
                + "&serviceKey=" + serviceKey;

        String xmlResponse = restTemplate.getForObject(url, String.class);

        // XML을 JSON으로 변환 및 포맷팅
        try {
            XmlMapper xmlMapper = new XmlMapper();
            JsonNode jsonNode = xmlMapper.readTree(xmlResponse);

            // JSON 포맷팅
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonNode);
        } catch (Exception e) {
            e.printStackTrace();
            return "Error converting XML to JSON";
        }
    }
}
