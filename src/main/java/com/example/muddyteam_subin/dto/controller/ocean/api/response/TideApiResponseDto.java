package com.example.muddyteam_subin.dto.controller.ocean.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

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

        @JsonProperty("data")
        private List<DataDto> dataDtoList;

    }
}
