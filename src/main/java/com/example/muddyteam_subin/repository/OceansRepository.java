package com.example.muddyteam_subin.repository;

import com.example.muddyteam_subin.entity.OceansEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OceansRepository extends JpaRepository<OceansEntity, Long> {

    @Query("SELECT o FROM OceansEntity o")
    List<OceansEntity> findAll();
}
