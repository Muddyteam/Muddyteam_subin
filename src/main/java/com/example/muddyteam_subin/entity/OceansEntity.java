package com.example.muddyteam_subin.entity;

import com.example.muddyteam_subin.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.checkerframework.checker.units.qual.A;
import org.checkerframework.checker.units.qual.C;
import org.hibernate.annotations.Where;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "oceans")
@Where(clause = "use_yn = true")
public class OceansEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ocean_id")
    private Long oceanId;

    @Column(name = "ocean_code", nullable = false)
    private String oceanCode;

    @Column(name = "ocean_name", nullable = false)
    private String oceanName;

    @Column(name = "ocean_lat", nullable = false)
    private String oceanLat;

    @Column(name = "ocean_lon", nullable = false)
    private String oceanLon;

    @Version
    private int version;
}
