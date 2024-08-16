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
public class MetaDto{

    @JsonProperty("obs_post_id")
    private String obsPostId;

    @JsonProperty("obs_post_name")
    private String obsPostName;

    @JsonProperty("obs_lat")
    private String obsLat;
    @JsonProperty("obs_lon")
    private String obsLon;

    @JsonProperty("obs_last_req_cnt")
    private String obsLastReqCnt;
}
