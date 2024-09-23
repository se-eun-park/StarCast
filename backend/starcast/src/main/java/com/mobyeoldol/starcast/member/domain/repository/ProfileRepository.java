package com.mobyeoldol.starcast.member.domain.repository;

import com.mobyeoldol.starcast.member.domain.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, String> {
    @Modifying(clearAutomatically = true)
    @Query("update Profile p set p.nickname = :nickname where p.profileUid = :profileUid")
    Optional<Profile> updateNicknameOnProfileById(String profileUid, String nickname);

    @Query("select profileUid from Profile where nickname = :nicknameToCheck")
    String findByNickname(String nicknameToCheck);
}