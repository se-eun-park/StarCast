package com.mobyeoldol.starcast.calendar.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="celestial_events")
public class CelestialEvents {

    @Id
    private String celestialEventUid;  // 천문현상 아이디 (년_월_일_시퀀스번호)

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "event")
    private String event;

    @Column(name = "celestial_event_hour")
    private int celestialEventHour;

    @Column(name = "celestial_event_min")
    private int celestialEventMin;
}
