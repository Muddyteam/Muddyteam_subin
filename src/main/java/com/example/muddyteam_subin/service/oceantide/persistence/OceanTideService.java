package com.example.muddyteam_subin.service.oceantide.persistence;

import com.example.muddyteam_subin.dto.jpa.oceantide.OceanTideDto;
import com.example.muddyteam_subin.dto.ocean.response.OceanTideResponseDto;
import com.example.muddyteam_subin.entity.OceanTideEntity;
import com.example.muddyteam_subin.mapper.OceanTideMapper;
import com.example.muddyteam_subin.repository.OceanTideRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OceanTideService {

    private final OceanTideRepository oceanTideRepository;
    private final OceanTideMapper oceanTideMapper;

    public OceanTideDto save(OceanTideDto oceanTideDto) {
        OceanTideEntity oceanTideEntity = oceanTideMapper.toEntity(oceanTideDto);
        return oceanTideMapper.toDto(oceanTideRepository.save(oceanTideEntity));
    }
}
