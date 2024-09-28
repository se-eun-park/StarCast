package com.mobyeoldol.starcast.place.domain.repository;

import com.mobyeoldol.starcast.place.domain.FavouriteSpot;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FavouriteSpotRepository extends JpaRepository<FavouriteSpot, String> {
    Optional<FavouriteSpot> findByPlace_PlaceUidAndProfile_ProfileUid(String placeUid, String profileUid);
}
