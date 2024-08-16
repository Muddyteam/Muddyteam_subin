package com.example.muddyteam_subin.service.oceantide.persistence;

import com.example.muddyteam_subin.dto.jpa.oceantide.OceanTideDto;
import com.example.muddyteam_subin.entity.OceanTideEntity;
import com.example.muddyteam_subin.mapper.OceanTideMapper;
import com.example.muddyteam_subin.repository.OceanTideRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.hibernate.proxy.EntityNotFoundDelegate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OceanTideService {

    private final OceanTideRepository oceanTideRepository;
    private final OceanTideMapper oceanTideMapper;

    @Transactional
    public OceanTideDto save(OceanTideDto oceanTideDto) {
        OceanTideEntity oceanTideEntity = oceanTideMapper.toEntity(oceanTideDto);
        return oceanTideMapper.toDto(oceanTideRepository.save(oceanTideEntity));
    }

    @Transactional(readOnly = true)
    public OceanTideDto findByOceanDayIdAndTphTime(Long oceanDayId, String tph_time){
        return oceanTideMapper.toDto(oceanTideRepository.findByOceanDayIdAndTphTime(oceanDayId, tph_time)
            .orElse(new OceanTideEntity()));
    }

    @Transactional(readOnly = true)
    public List<OceanTideDto> findByOceanDayId(Long oceanDayId){
        return oceanTideMapper.toDtoList(oceanTideRepository.findByOceanDayId(oceanDayId)
            .orElseThrow(() -> new EntityNotFoundException("OceanTide not found with oceanDayId : " + oceanDayId)));
    }
}
