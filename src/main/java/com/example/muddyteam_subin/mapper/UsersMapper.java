package com.example.muddyteam_subin.mapper;


import com.example.muddyteam_subin.common.mapper.GenericMapper;
import com.example.muddyteam_subin.dto.jpa.users.UsersDto;
import com.example.muddyteam_subin.entity.UsersEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {UserSessionsMapper.class})
public interface UsersMapper extends GenericMapper<UsersDto, UsersEntity> {

    @Mapping(source = "userSessionsEntity", target = "userSessionsDto")
    UsersDto toDto(UsersEntity usersEntity);

    @Mapping(source = "userSessionsDto", target = "userSessionsEntity")
    UsersEntity toEntity(UsersDto usersDto);
}
