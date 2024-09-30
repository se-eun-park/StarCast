package com.mobyeoldol.starcast.calendar.domain.repository;

import com.mobyeoldol.starcast.calendar.domain.CelestialEvents;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CalendarRepository extends JpaRepository<CelestialEvents, String> {
    List<CelestialEvents> findByCelestialEventUidStartingWith(String yearMonth);
}
