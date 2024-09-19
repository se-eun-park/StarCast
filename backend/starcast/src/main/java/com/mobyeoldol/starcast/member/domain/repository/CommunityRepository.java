package com.mobyeoldol.starcast.member.domain.repository;

import com.mobyeoldol.starcast.member.domain.Community;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommunityRepository extends JpaRepository<Community, String> {
    @Query("SELECT communityUid, place, title, content, createdDate, isDeleted FROM Community WHERE profile = :profileUid and isDeleted = false")
    Optional<List<Community>> findByProfileIdAndIsDeleted(@Param("profileId") String profileUid);
}