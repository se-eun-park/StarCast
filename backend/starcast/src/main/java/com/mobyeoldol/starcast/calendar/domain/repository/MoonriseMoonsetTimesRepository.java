package com.mobyeoldol.starcast.calendar.domain.repository;

import com.mobyeoldol.starcast.calendar.domain.MoonriseMoonsetTimes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MoonriseMoonsetTimesRepository extends JpaRepository<MoonriseMoonsetTimes, String> {
    Optional<MoonriseMoonsetTimes> findByMoonRiseSetTimeUid(String moonRiseSetTimeUid);
    Optional<MoonriseMoonsetTimes> findByMoonRiseSetTimeUidAndPlace_PlaceUid(String moonRiseSetTimeUid, String placeUid);
}
