package com.mobyeoldol.starcast.member.domain.repository;

import com.mobyeoldol.starcast.member.domain.Rank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RankRepository extends JpaRepository<Rank, String> {
//    @Query("SELECT rankUid, name FROM Rank WHERE rankUid = :rankUid")
//    Optional<Rank> findById(@Param("rankUid") String rankUid);
}