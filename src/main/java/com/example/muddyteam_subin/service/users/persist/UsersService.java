package com.example.muddyteam_subin.service.users.persist;

import com.example.muddyteam_subin.dto.jpa.users.UsersDto;
import com.example.muddyteam_subin.entity.UsersEntity;
import com.example.muddyteam_subin.mapper.UsersMapper;
import com.example.muddyteam_subin.repository.UsersRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UsersService {

    private final UsersRepository usersRepository;
    private final UsersMapper usersMapper;

    @Transactional
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
