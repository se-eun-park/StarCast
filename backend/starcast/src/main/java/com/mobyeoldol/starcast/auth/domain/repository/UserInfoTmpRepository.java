package com.mobyeoldol.starcast.auth.domain.repository;

import com.mobyeoldol.starcast.auth.domain.UserInfoTmp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserInfoTmpRepository extends JpaRepository<UserInfoTmp, String> {

}
