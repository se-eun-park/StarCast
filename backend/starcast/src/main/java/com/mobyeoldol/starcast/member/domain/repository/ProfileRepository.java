package com.mobyeoldol.starcast.member.domain.repository;

import com.mobyeoldol.starcast.auth.domain.Auth;
import com.mobyeoldol.starcast.member.domain.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, String> {
    Optional<Profile> findByNickname(String nickname);

    @Query("SELECT profileUid FROM Profile WHERE auth = :auth")
    Optional<Profile> findByAuth(Auth auth);
}