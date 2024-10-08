package com.mobyeoldol.starcast.place.domain.repository;

import com.mobyeoldol.starcast.place.domain.Plan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface PlanRepository extends JpaRepository<Plan, String> {
    List<Plan> findByProfile_ProfileUidAndIsDeletedFalse(String profileUid);
    List<Plan> findByProfile_ProfileUid(String profileUid);
    Optional<Plan> findByPlace_PlaceUidAndDateTimeBetweenAndIsDeletedFalse(
            String placeUid, LocalDateTime startDateTime, LocalDateTime endDateTime);
}
