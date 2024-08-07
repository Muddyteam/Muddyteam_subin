package com.example.muddyteam_subin.service.users.persist;

import com.example.muddyteam_subin.controller.KakaoController;
import com.example.muddyteam_subin.dto.jpa.users.UsersDto;
import com.example.muddyteam_subin.entity.UsersEntity;
import com.example.muddyteam_subin.mapper.UsersMapper;
import com.example.muddyteam_subin.repository.UsersRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UsersService {

    private final UsersRepository usersRepository;
    private final UsersMapper usersMapper;
    private final Logger LOGGER = LoggerFactory.getLogger(UsersService.class);

    @Transactional
    public UsersDto saveOrFindByUsername(UsersDto usersDto){
        LOGGER.info("username : {}", usersDto.getUsername());
        return usersMapper.toDto(usersRepository.findByUsername(usersDto.getUsername())
            .orElse(usersRepository.save(usersMapper.toEntity(usersDto))));
    }

    public UsersDto existsByUsername(UsersDto usersDto){
        boolean check = usersRepository.existsByUsername(usersDto.getUsername());
        if (!check){
            UsersEntity usersEntity = usersMapper.toEntity(usersDto);
            return usersMapper.toDto(usersRepository.save(usersEntity));
        } else {
            return usersMapper.toDto(usersRepository.findByUsername(usersDto.getUsername())
                .orElseThrow(() -> new EntityNotFoundException("Users not found with username : " + usersDto.getUsername())));
        }
    }

}
