package com.mobyeoldol.starcast.place.domain.repository;

import com.mobyeoldol.starcast.place.domain.Plan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlanRepository extends JpaRepository<Plan, String> {
    Optional<Plan> findByPlace_PlaceUidAndProfile_ProfileUid(String placeUid, String profileUid);
}
