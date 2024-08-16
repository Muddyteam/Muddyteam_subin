package com.example.muddyteam_subin.dto.ocean.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.checkerframework.checker.units.qual.A;
import org.mariadb.jdbc.BasePreparedStatement;

import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TideApiResponseDto {

    @JsonProperty("result")
    private ResultDto resultDto;

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class ResultDto{

        @JsonProperty("meta")
        private MetaDto metaDto;

        @Getter
        @Setter
        @AllArgsConstructor
        @NoArgsConstructor
        @Builder
        public static class MetaDto{
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

        @JsonProperty("data")
        private List<DataDto> dataDtos;

        @Getter
        @Setter
        @AllArgsConstructor
        @NoArgsConstructor
        @Builder
        public static class DataDto{
            @JsonProperty("hl_code")
            private String hlCode;

            @JsonProperty("tph_time")
            private String tphTime;

            @JsonProperty("tph_level")
            private String tphLevel;
        }
    }
}
