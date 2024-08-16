package com.example.muddyteam_subin.repository;

import com.example.muddyteam_subin.entity.OceanDayEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OceanDayRepository extends JpaRepository<OceanDayEntity, Long> {

    @Query("SELECT od FROM OceanDayEntity od WHERE od.oceans.oceanId = :oceanId AND od.day = :day")
    Optional<OceanDayEntity> findByOceanIdAndDay(@Param("oceanId") Long oceanId, @Param("day") String day);

}
