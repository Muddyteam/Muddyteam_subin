package com.example.muddyteam_subin.repository;


import com.example.muddyteam_subin.entity.UsersEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UsersRepository extends JpaRepository<UsersEntity, Long> {

    boolean existsByUsername(String username);

    @Query("SELECT u FROM UsersEntity u WHERE u.username = :username")
    Optional<UsersEntity> findByUsername(@Param("username") String username);

}
