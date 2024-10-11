package com.mobyeoldol.starcast.calendar.domain.repository;

import com.mobyeoldol.starcast.calendar.domain.LunarCycle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LunarCycleRepository extends JpaRepository<LunarCycle, String> {
    Optional<LunarCycle> findByLunarCycleUid(String lunarCycleUid);
}

