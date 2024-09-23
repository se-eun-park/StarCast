package com.mobyeoldol.starcast.member.domain.repository;

import com.mobyeoldol.starcast.member.domain.RandomNickname;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RandomNicknameRepository extends JpaRepository<RandomNickname, String> {
    @Query("select randomNicknameUid, adverb, adjective, sequence from RandomNickname where adjective = :adjective and adverb = :adverb")
    Optional<RandomNickname> findSequenceByAdjectiveAndAdverb(String adjective, String adverb);
}