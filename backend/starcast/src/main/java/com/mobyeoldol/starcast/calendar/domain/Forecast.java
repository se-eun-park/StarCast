package com.mobyeoldol.starcast.calendar.domain;

import com.mobyeoldol.starcast.place.domain.Place;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="forecast")
public class Forecast {
    @Id
    @Column(name = "forecast_uid", length = 47)
    private String forecastUid; // 단중기예보 아이디 (년_월_일_장소아이디)

    @ManyToOne
    @JoinColumn(name = "place_uid", nullable = false)
    private Place place;

    @Column(name = "sky_00")
    private Integer sky00;

    @Column(name = "pty_00")
    private Integer pty00;

    @Column(name = "humidity_00", precision = 5, scale = 2)
    private BigDecimal humidity00;

    @Column(name = "sky_01")
    private Integer sky01;

    @Column(name = "pty_01")
    private Integer pty01;

    @Column(name = "humidity_01", precision = 5, scale = 2)
    private BigDecimal humidity01;

    @Column(name = "sky_02")
    private Integer sky02;

    @Column(name = "pty_02")
    private Integer pty02;

    @Column(name = "humidity_02", precision = 5, scale = 2)
    private BigDecimal humidity02;

    @Column(name = "sky_21")
    private Integer sky21;

    @Column(name = "pty_21")
    private Integer pty21;

    @Column(name = "humidity_21", precision = 5, scale = 2)
    private BigDecimal humidity21;

    @Column(name = "sky_22")
    private Integer sky22;

    @Column(name = "pty_22")
    private Integer pty22;

    @Column(name = "humidity_22", precision = 5, scale = 2)
    private BigDecimal humidity22;

    @Column(name = "sky_23")
    private Integer sky23;

    @Column(name = "pty_23")
    private Integer pty23;

    @Column(name = "humidity_23", precision = 5, scale = 2)
    private BigDecimal humidity23;
}

