package com.example.muddyteam_subin.service.oceans.persistence;

import com.example.muddyteam_subin.dto.jpa.oceans.OceansDto;
import com.example.muddyteam_subin.mapper.OceansMapper;
import com.example.muddyteam_subin.repository.OceansRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OceansService {

    private final OceansRepository oceansRepository;
    private final OceansMapper oceansMapper;

    @Transactional(readOnly = true)
    public List<OceansDto> findAll(){
        return oceansMapper.toDtoList(oceansRepository.findAll());
    }
}
