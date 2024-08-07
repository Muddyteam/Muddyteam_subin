package com.example.muddyteam_subin.mapper;

import com.example.muddyteam_subin.common.mapper.GenericMapper;
import com.example.muddyteam_subin.dto.jpa.usersessions.UserSessionsDto;
import com.example.muddyteam_subin.entity.UserSessionsEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserSessionsMapper extends GenericMapper<UserSessionsDto, UserSessionsEntity> {

    @Mapping(source = "userId", target = "user.userId")
    UserSessionsEntity toEntity(UserSessionsDto userSessionsDto);

    @Mapping(source = "user.userId", target = "userId")
    UserSessionsDto toDto(UserSessionsEntity userSessionsEntity);
}
