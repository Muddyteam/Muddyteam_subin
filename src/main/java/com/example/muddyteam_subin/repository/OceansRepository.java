package com.example.muddyteam_subin.repository;

import com.example.muddyteam_subin.entity.OceansEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import org.springframework.data.repository.query.Param;

public interface OceansRepository extends JpaRepository<OceansEntity, Long> {

    @Query("SELECT o FROM OceansEntity o")
    List<OceansEntity> findAll();

    @Query("SELECT o FROM OceansEntity o WHERE o.oceanName = :oceanName")
    Optional<OceansEntity> findByOceanName(@Param("oceanName") String oceanName);
}
