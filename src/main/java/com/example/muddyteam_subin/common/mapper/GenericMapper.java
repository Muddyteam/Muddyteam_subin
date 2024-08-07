package com.example.muddyteam_subin.common.mapper;

public interface GenericMapper<D, E> {

    D toDto(E entity);

    E toEntity(D dto);

}
