package com.mobyeoldol.starcast.calendar.domain.repository;

import com.mobyeoldol.starcast.calendar.domain.Forecast;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ForecastRepository extends JpaRepository<Forecast, String> {
    Optional<Forecast> findByForecastUid(String date);
}
