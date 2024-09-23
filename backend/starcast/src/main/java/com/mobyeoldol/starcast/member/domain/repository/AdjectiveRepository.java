package com.mobyeoldol.starcast.member.domain.repository;

import com.mobyeoldol.starcast.member.domain.Adjective;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AdjectiveRepository extends JpaRepository<Adjective, String> {}