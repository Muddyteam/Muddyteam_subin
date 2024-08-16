package com.example.muddyteam_subin.service.oceanday.persistence;

import com.example.muddyteam_subin.dto.jpa.oceanday.OceanDayDto;
import com.example.muddyteam_subin.entity.OceanDayEntity;
import com.example.muddyteam_subin.mapper.OceanDayMapper;
import com.example.muddyteam_subin.repository.OceanDayRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OceanDayService {

    private final OceanDayRepository oceanDayRepository;
    private final OceanDayMapper oceanDayMapper;

    public OceanDayDto save(OceanDayDto oceanDayDto) {
        OceanDayEntity oceanDayEntity = oceanDayMapper.toEntity(oceanDayDto);
        return oceanDayMapper.toDto(oceanDayRepository.save(oceanDayEntity));
    }
}
