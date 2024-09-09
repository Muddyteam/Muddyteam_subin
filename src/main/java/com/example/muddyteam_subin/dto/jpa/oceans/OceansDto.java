package com.example.muddyteam_subin.dto.jpa.oceans;

import lombok.*;
import org.springframework.stereotype.Service;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OceansDto {

    private Long oceanId;
    private String oceanCode;
    private String oceanName;
    private String oceanLat;
    private String oceanLon;
    private int version;
}
