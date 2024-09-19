package com.mobyeoldol.starcast.member.application.service;

import com.mobyeoldol.starcast.member.domain.*;
import com.mobyeoldol.starcast.member.domain.repository.*;
import com.mobyeoldol.starcast.member.presentation.exception.CustomErrorCode;
import com.mobyeoldol.starcast.member.presentation.exception.CustomException;
import com.mobyeoldol.starcast.member.presentation.response.MyInfoResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final ProfileRepository profileRepository;
    private final RankRepository rankRepository;
    private final PlaceRepository placeRepository;
    private final CommunityRepository communityRepository;
    private final FavouriteSpotRepository favouriteSpotRepository;

    @Override
    public MyInfoResponse getMemberInfo(String bearerToken) {
//        String profileUid = authenticateMember(bearerToken);
        String profileUid = "testProfileId1234";
        Profile profile = profileRepository.findById(profileUid)
                .orElseThrow(()->new CustomException(CustomErrorCode.PROFILE_INFO_NOT_FOUND));

        Rank rank = rankRepository.findById(profile.getRank().getRankUid())
                .orElseThrow(()->new CustomException(CustomErrorCode.RANK_INFO_NOT_FOUND));

        Place place = favouriteSpotRepository.findByProfileIdAndSpotType(profileUid)
                .orElseThrow(()->new CustomException(CustomErrorCode.MY_PLACE_INFO_NOT_FOUND));

        String placeAddress = place.getAddress1() + " " + place.getAddress2() + " " + place.getAddress3();

        return MyInfoResponse.builder()
                .name(profile.getName())
                .nickname(profile.getNickname())
                .email(profile.getEmail())
                .profileImage(profile.getProfileImgNum())
                .address(placeAddress)
                .myCurExp(profile.getExp())
                .rank(rank.getName())
                .build();
    }

    /*
    public List<CommunityByMemberResponse> getCommunityListByMember(String bearerToken) {
//        String profileUid = authenticateMember(bearerToken);
        String profileUid = "testProfileId1234";
        List<Community> communityList = communityRepository.findByProfileIdAndIsDeleted(profileUid)
                .orElseThrow(()->new CustomException(CustomErrorCode.COMMUNITY_LIST_NOT_FOUND));



        Post post = postRepository.findByPostIdAndDeleteFlagPostFalse(postId)
                .orElseThrow(() -> new CustomException(CustomErrorCode.POST_NOT_FOUND));

        List<Comment> comments = commentRepository.findByPostIdAndDeleteFlagCommentFalse(post.getPostId());
        List<CommentResponse> commentResponseList = new ArrayList<>();

        for (Comment comment : comments) {
            CommentResponse commentResponse = CommentResponse.builder()
                    .commentId(comment.getCommentId())
                    .nickName(comment.getNickName())
                    .commentContent(comment.getCommentContent())
                    .commentCreatedDate(comment.getCommentCreatedDate())
                    .build();
            commentResponseList.add(commentResponse);
        }

        return PostResponse.fromEntity(post, commentResponseList);



        return CommunityByMemberResponse.builder()
                .communityUid(profile.getName())
                .nickname(profile.getNickname())
                .email(profile.getEmail())
                .profileImage(profile.getProfileImgNum())
                .address(placeAddress)
                .myCurExp(profile.getExp())
                .rank(rank.getName())
                .build();
    }
    */
}
