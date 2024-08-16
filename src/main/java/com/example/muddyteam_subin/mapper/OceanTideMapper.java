package com.example.muddyteam_subin.mapper;

import com.example.muddyteam_subin.common.mapper.GenericMapper;
import com.example.muddyteam_subin.dto.jpa.oceanday.OceanDayDto;
import com.example.muddyteam_subin.dto.jpa.oceantide.OceanTideDto;
import com.example.muddyteam_subin.entity.OceanDayEntity;
import com.example.muddyteam_subin.entity.OceanTideEntity;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OceanTideMapper extends GenericMapper<OceanTideDto, OceanTideEntity> {

    @Mapping(source = "oceanDay.oceanDayId", target = "oceanDayId")
    OceanTideDto toDto(OceanTideEntity oceanTideEntity);

    @Mapping(source = "oceanDayId", target = "oceanDay.oceanDayId")
    OceanTideEntity toEntity(OceanTideDto oceanTideDto);

    @Mapping(source = "oceanDay.oceanDayId", target = "oceanDayId")
    List<OceanTideDto> toDtoList(List<OceanTideEntity> oceanTideEntity);

    @Mapping(source = "oceanDayId", target = "oceanDay.oceanDayId")
    List<OceanTideEntity> toEntityList(List<OceanTideDto> oceanTideDtos);
}
