package com.mobyeoldol.starcast.notice.domain.repository;

import com.mobyeoldol.starcast.notice.domain.Consent;
import com.mobyeoldol.starcast.notice.domain.enums.ConsentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ConsentRepository extends JpaRepository<Consent, String> {
    Optional<Consent> findByProfile_ProfileUidAndType(String profileUid, ConsentType type);
}
