package com.mobyeoldol.starcast.calendar.domain;

import com.mobyeoldol.starcast.place.domain.Place;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="moonrise_moonset_times")
public class MoonriseMoonsetTimes {
    @Id
    @Column(name = "moon_rise_set_time_uid", length = 47)
    private String moonRiseSetTimeUid; // 위치별출몰시각 아이디 (년_월_일_장소아이디)

    @ManyToOne
    @JoinColumn(name = "place_uid", nullable = false)
    private Place place;

    @Column(name = "moonrise_hour")
    private Integer moonriseHour;

    @Column(name = "moonrise_min")
    private Integer moonriseMin;

    @Column(name = "moonset_hour")
    private Integer moonsetHour;

    @Column(name = "moonset_min")
    private Integer moonsetMin;
}

