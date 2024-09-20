package com.mobyeoldol.starcast.member.application.service;

import com.mobyeoldol.starcast.member.application.dto.AddressDto;
import com.mobyeoldol.starcast.member.application.dto.AuthorDto;
import com.mobyeoldol.starcast.member.domain.*;
import com.mobyeoldol.starcast.member.domain.repository.*;
import com.mobyeoldol.starcast.member.presentation.exception.CustomErrorCode;
import com.mobyeoldol.starcast.member.presentation.exception.CustomException;
import com.mobyeoldol.starcast.member.presentation.response.CommunityByMemberResponse;
import com.mobyeoldol.starcast.member.presentation.response.MyInfoResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final ProfileRepository profileRepository;
    private final RankRepository rankRepository;
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


    public List<CommunityByMemberResponse> getCommunityListByMember(String bearerToken) {
//        String profileUid = authenticateMember(bearerToken);
        String profileUid = "testProfileId1234";
        List<Community> communityList = communityRepository.findByProfileIdAndIsDeleted(profileUid)
                .orElseThrow(()->new CustomException(CustomErrorCode.COMMUNITY_LIST_NOT_FOUND));
        List<CommunityByMemberResponse> communities = new ArrayList<>();

        for (Community community : communityList) {
            AuthorDto authorDto = AuthorDto.builder()
                    .profile_uid(community.getProfile().getProfileUid())
                    .nickname(community.getProfile().getNickname())
                    .profileImage(community.getProfile().getProfileImgNum())
                    .build();

            AddressDto addressDto = AddressDto.builder()
                    .address1(community.getPlace().getAddress1())
                    .address2(community.getPlace().getAddress2())
                    .address3(community.getPlace().getAddress3())
                    .build();

            CommunityByMemberResponse response = CommunityByMemberResponse.builder()
                    .communityUid(community.getCommunityUid())
                    .mainImage(community.getCommunityImages().getFirst().getUrl())
                    .title(community.getTitle())
                    .author(authorDto)
                    .date_time(community.getCreatedDate())
                    .address(addressDto)
                    .castarPoint(community.getPlan().getCastarPoint())
                    .build();

            communities.add(response);
        }

        return communities;
    }
}
