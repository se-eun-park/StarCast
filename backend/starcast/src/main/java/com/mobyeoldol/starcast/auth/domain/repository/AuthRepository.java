package com.mobyeoldol.starcast.auth.domain.repository;

import com.mobyeoldol.starcast.auth.domain.Auth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthRepository extends JpaRepository<Auth, String> {

    @Query("SELECT a FROM Auth a WHERE a.kakaoUid = :kakaoUid")
    Optional<Auth> findByKakaoUid(String kakaoUid);
}
