package com.example.muddyteam_subin.entity;

import com.example.muddyteam_subin.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Where;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "ocean_tide")
@Where(clause = "use_yn = true")
public class OceanTideEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ocean_tide_id")
    private Long oceanTideId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ocean_day_id")
    private OceanDayEntity oceanDay;

    @Column(name = "hl_code", nullable = false)
    private String hlCode;

    @Column(name = "tph_time", nullable = false)
    private String tphTime;

    @Column(name = "tph_level", nullable = false)
    private String tphLevel;

    @Version
    private int version;
}
