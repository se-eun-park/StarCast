package com.mobyeoldol.starcast.place.domain.repository;

import com.mobyeoldol.starcast.place.domain.FavouriteSpot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FavouriteSpotRepository extends JpaRepository<FavouriteSpot, String> {
    Optional<FavouriteSpot> findByPlace_PlaceUidAndProfile_ProfileUid(String placeUid, String profileUid);
    List<FavouriteSpot> findByProfile_ProfileUid(String profileUid);
    List<FavouriteSpot> findByProfile_ProfileUidAndIsDeletedFalse(String profileUid);

}
