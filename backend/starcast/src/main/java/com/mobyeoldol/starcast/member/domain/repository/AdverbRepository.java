package com.mobyeoldol.starcast.member.domain.repository;

import com.mobyeoldol.starcast.member.domain.Adverb;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AdverbRepository extends JpaRepository<Adverb, String> {}