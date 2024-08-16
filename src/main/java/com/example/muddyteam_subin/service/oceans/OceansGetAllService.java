package com.example.muddyteam_subin.service.oceans;

import com.example.muddyteam_subin.dto.jpa.oceans.OceansDto;
import com.example.muddyteam_subin.service.oceans.persistence.OceansService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OceansGetAllService {

    private final OceansService oceansService;

    public List<OceansDto> getAllOceans() {
        return oceansService.findAll();
    }
}
