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
    @Column(name = "forecast_uid", length = 36)
    private String forecastUid;

    @ManyToOne
    @JoinColumn(name = "place_uid", nullable = false)
    private Place place;

    @Column(name = "sky_00", precision = 5, scale = 2)
    private BigDecimal sky00;

    @Column(name = "pty_00", precision = 5, scale = 2)
    private BigDecimal pty00;

    @Column(name = "humidity_00", precision = 5, scale = 2)
    private BigDecimal humidity00;

    @Column(name = "sky_01", precision = 5, scale = 2)
    private BigDecimal sky01;

    @Column(name = "pty_01", precision = 5, scale = 2)
    private BigDecimal pty01;

    @Column(name = "humidity_01", precision = 5, scale = 2)
    private BigDecimal humidity01;

    @Column(name = "sky_02", precision = 5, scale = 2)
    private BigDecimal sky02;

    @Column(name = "pty_02", precision = 5, scale = 2)
    private BigDecimal pty02;

    @Column(name = "humidity_02", precision = 5, scale = 2)
    private BigDecimal humidity02;

    @Column(name = "sky_21", precision = 5, scale = 2)
    private BigDecimal sky21;

    @Column(name = "pty_21", precision = 5, scale = 2)
    private BigDecimal pty21;

    @Column(name = "humidity_21", precision = 5, scale = 2)
    private BigDecimal humidity21;

    @Column(name = "sky_22", precision = 5, scale = 2)
    private BigDecimal sky22;

    @Column(name = "pty_22", precision = 5, scale = 2)
    private BigDecimal pty22;

    @Column(name = "humidity_22", precision = 5, scale = 2)
    private BigDecimal humidity22;

    @Column(name = "sky_23", precision = 5, scale = 2)
    private BigDecimal sky23;

    @Column(name = "pty_23", precision = 5, scale = 2)
    private BigDecimal pty23;

    @Column(name = "humidity_23", precision = 5, scale = 2)
    private BigDecimal humidity23;
}
