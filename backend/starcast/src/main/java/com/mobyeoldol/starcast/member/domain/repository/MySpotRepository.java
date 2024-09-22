package com.mobyeoldol.starcast.member.domain.repository;

import com.mobyeoldol.starcast.member.domain.MySpot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MySpotRepository extends JpaRepository<MySpot, String> {
    @Modifying(clearAutomatically = true)
    @Query("update MySpot set place.placeUid = :placeUid, modifiedDate = now() where profile.profileUid = :profileUid")
    Optional<MySpot> updatePlaceUidOnMySpotByProfileUid(String profileUid, String placeUid);
}