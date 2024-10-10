package com.mobyeoldol.starcast.calendar.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.math.BigDecimal;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="lunar_cycle")
public class LunarCycle {
    @Id
    @Column(name = "lunar_cycle_uid", length = 36)
    private String lunarCycleUid;

    @Column(name = "lun_age", precision = 5, scale = 2)
    private BigDecimal lunarAge;
}

