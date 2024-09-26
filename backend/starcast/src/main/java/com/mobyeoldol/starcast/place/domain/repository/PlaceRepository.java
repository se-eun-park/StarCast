package com.mobyeoldol.starcast.place.domain.repository;

import com.mobyeoldol.starcast.place.domain.Place;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlaceRepository  extends JpaRepository<Place, String> {
    Optional<Place> findByPlaceUid(String placeUid);

    Optional<Place> findByAddress1AndAddress2AndAddress3AndAddress4(String address1, String address2, String address3, String address4);
}
