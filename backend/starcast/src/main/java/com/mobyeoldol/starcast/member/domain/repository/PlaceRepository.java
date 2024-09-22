package com.mobyeoldol.starcast.member.domain.repository;

import com.mobyeoldol.starcast.member.domain.Place;
import com.mobyeoldol.starcast.member.domain.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlaceRepository extends JpaRepository<Profile, String> {
    @Query("SELECT placeUid FROM Place WHERE address1 = :address1 and address2 = :address2 and address3 = :address3")
    Optional<Place> findByAddress(String address1, String address2, String address3);
}