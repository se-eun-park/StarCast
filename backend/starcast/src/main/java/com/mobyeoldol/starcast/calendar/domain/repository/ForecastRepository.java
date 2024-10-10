package com.mobyeoldol.starcast.calendar.domain.repository;

import com.mobyeoldol.starcast.calendar.domain.Forecast;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ForecastRepository extends JpaRepository<Forecast, String> {
    Optional<Forecast> findByForecastUidAndPlace_PlaceUid(String forecastUid, String placeUid);
    Optional<Forecast> findByForecastUid(String date);
}
