package com.example.muddyteam_subin.service.usersessions.persist;

import com.example.muddyteam_subin.dto.jpa.usersessions.UserSessionsDto;
import com.example.muddyteam_subin.entity.UserSessionsEntity;
import com.example.muddyteam_subin.mapper.UserSessionsMapper;
import com.example.muddyteam_subin.repository.UserSessionsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserSessionsService {

    private final UserSessionsRepository userSessionsRepository;
    private final UserSessionsMapper userSessionsMapper;

    @Transactional
    public UserSessionsDto save(UserSessionsDto userSessionsDto){
        UserSessionsEntity userSessionsEntity = userSessionsMapper.toEntity(userSessionsDto);
        return userSessionsMapper.toDto(userSessionsRepository.save(userSessionsEntity));
    }

    public UserSessionsDto existsByuserId(Long userId){
        return userSessionsMapper.toDto(userSessionsRepository.findByUserId(userId)
            .orElse(UserSessionsEntity.builder().build()));
    }

}
