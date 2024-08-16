package com.example.muddyteam_subin.repository;

import com.example.muddyteam_subin.entity.OceanTideEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OceanTideRepository extends JpaRepository<OceanTideEntity, Long> {

    @Query("SELECT ot FROM OceanTideEntity ot WHERE ot.oceanDay.oceanDayId = :ocean_day_id AND ot.tphTime = :tph_time")
    Optional<OceanTideEntity> findByOceanDayIdAndTphTime(@Param("ocean_day_id") Long oceanDayId, @Param("tph_time") String tphTime);

    @Query("SELECT ot FROM OceanTideEntity ot WHERE ot.oceanDay.oceanDayId = :oceanDayId")
    Optional<List<OceanTideEntity>> findByOceanDayId(@Param("oceanDayId") Long oceanDayId);

}
