package com.mobyeoldol.starcast.community.domain.repository;

import com.mobyeoldol.starcast.community.domain.Reaction;
import com.mobyeoldol.starcast.place.domain.enums.ReactionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReactionRepository extends JpaRepository<Reaction, String> {
    long countByCommunity_CommunityUidAndReactionType(String communityUid, ReactionType reactionType);
}

