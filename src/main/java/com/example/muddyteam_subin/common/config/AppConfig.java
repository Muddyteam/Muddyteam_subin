package com.example.muddyteam_subin.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class AppConfig {

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();

        List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();

        // Add JSON message converter
        MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
        jsonConverter.setDefaultCharset(StandardCharsets.UTF_8);
        messageConverters.add(jsonConverter);

        // Add String message converter for fallback
        messageConverters.add(new StringHttpMessageConverter(StandardCharsets.UTF_8));

        restTemplate.setMessageConverters(messageConverters);
        return restTemplate;
    }
}