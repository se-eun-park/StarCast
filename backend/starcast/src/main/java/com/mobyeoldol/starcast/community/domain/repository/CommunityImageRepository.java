package com.mobyeoldol.starcast.community.domain.repository;

import com.mobyeoldol.starcast.community.domain.CommunityImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommunityImageRepository  extends JpaRepository<CommunityImage, String> {
}
