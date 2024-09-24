package com.mobyeoldol.starcast.member.application.service;

import com.mobyeoldol.starcast.member.application.dto.AddressDto;
import com.mobyeoldol.starcast.member.application.dto.AuthorDto;
import com.mobyeoldol.starcast.member.domain.*;
import com.mobyeoldol.starcast.member.domain.repository.*;
import com.mobyeoldol.starcast.member.presentation.response.CommunityByMemberResponse;
import com.mobyeoldol.starcast.member.presentation.response.MyInfoResponse;
import com.mobyeoldol.starcast.place.domain.Place;
import com.mobyeoldol.starcast.place.domain.repository.MySpotRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final ProfileRepository profileRepository;
    private final RankRepository rankRepository;
    private final CommunityRepository communityRepository;
    private final MySpotRepository mySpotRepository;

    @Override
    public MyInfoResponse getMemberInfo(String profileUid) {

        log.info("[내 정보 가져오기 API] 1. 유저 정보 인증");
        Optional<Profile> profile = profileRepository.findById(profileUid);
        if (profile.isEmpty()) throw new RuntimeException("존재하지 않는 유저입니다.");

        log.info("[내 정보 가져오기 API] 2. 유저 랭크 확인");
        Optional<Rank> rank = rankRepository.findById(profile.get().getRank().getRankUid());
        if (rank.isEmpty()) throw new RuntimeException("존재하지 않는 랭크입니다.");

        log.info("[내 정보 가져오기 API] 3. 유저의 내 장소 확인");
        Optional<Place> place = mySpotRepository.findByProfileIdAndSpotType(profileUid);
        if (place.isEmpty()) throw new RuntimeException("존재하지 않는 장소입니다.");

        String placeAddress = place.get().getAddress1() + " " + place.get().getAddress2() + " " + place.get().getAddress3();

        log.info("[내 정보 가져오기 API] 4. 내 정보 리턴");
        return MyInfoResponse.builder()
                .name(profile.get().getName())
                .nickname(profile.get().getNickname())
                .email(profile.get().getEmail())
                .profileImage(profile.get().getProfileImgNum())
                .address(placeAddress)
                .myCurExp(profile.get().getExp())
                .rank(rank.get().getName())
                .build();
    }

    public List<CommunityByMemberResponse> getCommunityListByMember(String profileUid) {

        log.info("[내가 작성한 글 리스트 가져오기 API] 1. 내가 작성한 글 리스트 가져오기");
        Optional<List<Community>> communityList = communityRepository.findByProfileIdAndIsDeleted(profileUid);
        if (communityList.isEmpty()) throw new RuntimeException("글 리스트가 존재하지 않습니다.");

        List<CommunityByMemberResponse> communities = new ArrayList<>();

        log.info("[내가 작성한 글 리스트 가져오기 API] 2. 리스트를 응답 형식으로 가공");
        for (Community community : communityList.get()) {
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

        log.info("[내가 작성한 글 리스트 가져오기 API] 3. 글 리스트를 응답 형식으로 가공 후 리턴");
        return communities;
    }
}
