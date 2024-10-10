package com.mobyeoldol.starcast.place.domain.repository;

import com.mobyeoldol.starcast.place.domain.Plan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlanRepository extends JpaRepository<Plan, String> {
    List<Plan> findByProfile_ProfileUidAndIsDeletedFalse(String profileUid);
    List<Plan> findByProfile_ProfileUid(String profileUid);
}
