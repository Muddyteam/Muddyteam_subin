package com.example.muddyteam_subin.entity;

import com.example.muddyteam_subin.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.checkerframework.checker.units.qual.C;
import org.hibernate.annotations.Where;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "ocean_day")
@Where(clause = "use_yn = true")
public class OceanDayEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ocean_day_id")
    private Long oceanDayId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ocean_id")
    private OceansEntity oceans;

    @Column(name = "day", nullable = false)
    private String day;

    @Version
    private int version;
}
