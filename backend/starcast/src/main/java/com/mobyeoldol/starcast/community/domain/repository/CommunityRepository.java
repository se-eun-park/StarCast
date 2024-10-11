package com.mobyeoldol.starcast.community.domain.repository;

import com.mobyeoldol.starcast.community.domain.Community;
import com.mobyeoldol.starcast.place.domain.enums.ReactionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommunityRepository extends JpaRepository<Community, String> {
    @Query("SELECT c FROM Community c WHERE c.profile.profileUid = :profileUid AND c.isDeleted = false")
    Optional<List<Community>> findByProfileIdAndIsDeleted(@Param("profileUid") String profileUid);

    List<Community> findByPlace_PlaceUid(String placeUid);
    int countByPlace_PlaceUid(String placeUid);

    List<Community> findByIsDeletedFalseOrderByCreatedDateDesc();

    @Query("SELECT c FROM Community c JOIN c.reactions r WHERE r.reactionType = :reactionType GROUP BY c ORDER BY COUNT(r) DESC")
    List<Community> findTopByReactionType(@Param("reactionType") ReactionType reactionType);

}