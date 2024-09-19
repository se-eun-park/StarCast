package com.mobyeoldol.starcast.member.domain.repository;

import com.mobyeoldol.starcast.member.domain.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, String> {
//    @Query("SELECT a.authId FROM Profile WHERE a.kakaoId = :profileId")
//    Optional<Profile> findById(@Param("profileId") String profileId);
}