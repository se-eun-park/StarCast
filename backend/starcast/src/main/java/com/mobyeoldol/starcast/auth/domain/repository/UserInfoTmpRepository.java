package com.mobyeoldol.starcast.auth.domain.repository;

import com.mobyeoldol.starcast.auth.domain.Auth;
import com.mobyeoldol.starcast.auth.domain.UserInfoTmp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserInfoTmpRepository extends JpaRepository<UserInfoTmp, String> {

    @Query("SELECT u FROM UserInfoTmp u WHERE u.auth = :auth")
    Optional<UserInfoTmp> findByAuth(Auth auth);
}
