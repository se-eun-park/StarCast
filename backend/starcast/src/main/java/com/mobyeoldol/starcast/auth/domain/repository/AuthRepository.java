package com.mobyeoldol.starcast.auth.domain.repository;

import com.mobyeoldol.starcast.auth.domain.Auth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthRepository extends JpaRepository<Auth, String> {
    @Query("SELECT a.authId FROM Auth a WHERE a.kakaoId = :kakaoId")
    Optional<Auth> findAuthByKakaoId(@Param("kakaoId") Long kakaoId);
}

