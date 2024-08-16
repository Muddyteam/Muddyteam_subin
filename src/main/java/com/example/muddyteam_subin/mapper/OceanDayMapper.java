package com.example.muddyteam_subin.mapper;

import com.example.muddyteam_subin.common.mapper.GenericMapper;
import com.example.muddyteam_subin.dto.jpa.oceanday.OceanDayDto;
import com.example.muddyteam_subin.entity.OceanDayEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OceanDayMapper extends GenericMapper<OceanDayDto, OceanDayEntity> {

    @Mapping(source = "oceans.oceanId", target = "oceanId")
    OceanDayDto toDto(OceanDayEntity oceanDayEntity);

    @Mapping(source = "oceanId", target = "oceans.oceanId")
    OceanDayEntity toEntity(OceanDayDto oceanDayDto);

}
