package com.mobyeoldol.starcast.member.application.service;

import com.mobyeoldol.starcast.member.domain.*;
import com.mobyeoldol.starcast.member.domain.repository.*;
import com.mobyeoldol.starcast.member.presentation.exception.CustomErrorCode;
import com.mobyeoldol.starcast.member.presentation.exception.CustomException;
import com.mobyeoldol.starcast.member.presentation.request.UpdateMySpotRequest;
import com.mobyeoldol.starcast.member.presentation.response.UpdateMyNicknameResponse;
import com.mobyeoldol.starcast.member.presentation.response.UpdateMyProfileImageResponse;
import com.mobyeoldol.starcast.member.presentation.response.UpdateMySpotResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Slf4j
@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final ProfileRepository profileRepository;
    private final MySpotRepository mySpotRepository;
    private final PlaceRepository placeRepository;

    @Override
    public UpdateMyNicknameResponse updateMyNickname(String bearerToken, String nickname) {
//        String profileUid = authenticateMember(bearerToken);
        String profileUid = "testProfileId1234";
        Profile profile = profileRepository.findById(profileUid)
                .orElseThrow(()->new CustomException(CustomErrorCode.PROFILE_INFO_NOT_FOUND));

        Optional<Profile> updatedProfile = profileRepository.updateNicknameOnProfileById(profile.getProfileUid(), nickname);
        if(updatedProfile.isPresent()) return UpdateMyNicknameResponse.builder().nickname(updatedProfile.get().getNickname()).build();
        else throw new CustomException(CustomErrorCode.UPDATE_NICKNAME_FAILED);
    }

    @Override
    public UpdateMyProfileImageResponse UpdateMyProfileImage(String bearerToken, String imageNum) {
//        String profileUid = authenticateMember(bearerToken);
        String profileUid = "testProfileId1234";
        Profile profile = profileRepository.findById(profileUid)
                .orElseThrow(()->new CustomException(CustomErrorCode.PROFILE_INFO_NOT_FOUND));

        Optional<Profile> updatedProfile = profileRepository.updateImageOnProfileById(profile.getProfileUid(), imageNum);
        if(updatedProfile.isPresent()) return UpdateMyProfileImageResponse.builder().image(updatedProfile.get().getProfileImgNum()).build();
        else throw new CustomException(CustomErrorCode.UPDATE_PROFILE_IMAGE_FAILED);
    }

    @Override
    public UpdateMySpotResponse UpdateMySpot(String bearerToken, UpdateMySpotRequest request) {
//        String profileUid = authenticateMember(bearerToken);
        String profileUid = "testProfileId1234";
        Profile profile = profileRepository.findById(profileUid)
                .orElseThrow(()->new CustomException(CustomErrorCode.PROFILE_INFO_NOT_FOUND));

        Optional<Place> place = placeRepository.findByAddress(request.getAddress1(), request.getAddress2(), request.getAddress3());

        if(place.isPresent()) {
            Optional<MySpot> updatedMySpot = mySpotRepository.updatePlaceUidOnMySpotByProfileUid(profile.getProfileUid(), place.get().getPlaceUid());
            if(updatedMySpot.isPresent()) {
                return UpdateMySpotResponse.builder()
                        .address(updatedMySpot.get().getPlace().getAddress1()
                                + updatedMySpot.get().getPlace().getAddress2()
                                + updatedMySpot.get().getPlace().getAddress3())
                        .build();
            }
            else throw new CustomException(CustomErrorCode.UPDATE_MYSPOT_FAILED);
        }
        else throw new CustomException(CustomErrorCode.PLACE_NOT_FOUND);
    }
}
