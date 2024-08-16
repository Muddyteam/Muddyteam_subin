package com.example.muddyteam_subin.service.oceanday.persistence;

import com.example.muddyteam_subin.dto.jpa.oceanday.OceanDayDto;
import com.example.muddyteam_subin.entity.OceanDayEntity;
import com.example.muddyteam_subin.mapper.OceanDayMapper;
import com.example.muddyteam_subin.repository.OceanDayRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OceanDayService {

    private final OceanDayRepository oceanDayRepository;
    private final OceanDayMapper oceanDayMapper;

    @Transactional
    public OceanDayDto save(OceanDayDto oceanDayDto) {
        OceanDayEntity oceanDayEntity = oceanDayMapper.toEntity(oceanDayDto);
        return oceanDayMapper.toDto(oceanDayRepository.save(oceanDayEntity));
    }

    @Transactional(readOnly = true)
    public OceanDayDto findByOceanIdAndDayOrElseNew(Long oceanId, String day){
        return oceanDayMapper.toDto(oceanDayRepository.findByOceanIdAndDay(oceanId, day)
            .orElse(new OceanDayEntity()));
    }

    @Transactional(readOnly = true)
    public OceanDayDto findByOceanIdAndDay(Long oceanId, String day){
        return oceanDayMapper.toDto(oceanDayRepository.findByOceanIdAndDay(oceanId, day)
            .orElseThrow(() -> new EntityNotFoundException("OceanDay not found with oceanId : " + oceanId + " , day : " + day)));
    }
}
