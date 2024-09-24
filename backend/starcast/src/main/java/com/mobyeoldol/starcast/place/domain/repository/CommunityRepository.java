package com.mobyeoldol.starcast.place.domain.repository;

import java.util.List;
import com.mobyeoldol.starcast.place.domain.Community;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommunityRepository  extends JpaRepository<Community, String> {
    List<Community> findByPlace_PlaceUid(String placeUid);

    int countByPlace_PlaceUid(String placeUid);
}