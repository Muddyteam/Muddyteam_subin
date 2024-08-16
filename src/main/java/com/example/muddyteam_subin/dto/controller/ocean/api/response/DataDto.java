package com.example.muddyteam_subin.dto.controller.ocean.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
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
public class DataDto{
    @JsonProperty("hl_code")
    private String hlCode;

    @JsonProperty("tph_time")
    private String tphTime;

    @JsonProperty("tph_level")
    private String tphLevel;
}

