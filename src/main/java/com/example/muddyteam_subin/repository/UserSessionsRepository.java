package com.example.muddyteam_subin.repository;


import com.example.muddyteam_subin.entity.UserSessionsEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserSessionsRepository extends JpaRepository<UserSessionsEntity, Long> {

    @Query("SELECT us FROM UserSessionsEntity us WHERE us.user.userId = :userId")
    Optional<UserSessionsEntity> findByUserId(@Param("userId") Long userId);

}
