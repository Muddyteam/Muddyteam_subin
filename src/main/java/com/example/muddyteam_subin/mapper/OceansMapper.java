package com.example.muddyteam_subin.mapper;

import com.example.muddyteam_subin.common.mapper.GenericMapper;
import com.example.muddyteam_subin.dto.jpa.oceans.OceansDto;
import com.example.muddyteam_subin.entity.OceansEntity;
import lombok.Getter;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OceansMapper extends GenericMapper<OceansDto, OceansEntity> {

    List<OceansDto> toDtoList(List<OceansEntity> oceansEntities);

    List<OceansEntity> toEntityList(List<OceansDto> oceansDtos);

}
