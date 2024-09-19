package com.mobyeoldol.starcast.member.domain.repository;

import com.mobyeoldol.starcast.member.domain.Place;
import com.mobyeoldol.starcast.member.domain.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FavouriteSpotRepository extends JpaRepository<Profile, String> {
    @Query("SELECT place FROM FavouriteSpot WHERE profile = :profileId and spotType = 'MY_PLACE'")
    Optional<Place> findByProfileIdAndSpotType(@Param("profileId") String profileId);
}