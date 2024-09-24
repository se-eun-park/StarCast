package com.mobyeoldol.starcast.place.domain.repository;

import com.mobyeoldol.starcast.place.domain.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaceRepository extends JpaRepository<Place, String> {
//    @Query("SELECT a.authId FROM Profile WHERE a.kakaoId = :profileId")
//    Optional<Profile> findById(@Param("profileId") String profileId);
}