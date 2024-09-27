package com.mobyeoldol.starcast.place.domain.repository;

import com.mobyeoldol.starcast.place.domain.FavouriteSpot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FavouriteSpotRepository extends JpaRepository<FavouriteSpot, String> {
    Optional<FavouriteSpot> findByPlaceUidAndProfileUid(String placeUid, String profileUid);
}
