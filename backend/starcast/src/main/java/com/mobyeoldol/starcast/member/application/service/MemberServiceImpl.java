package com.mobyeoldol.starcast.member.application.service;

import com.mobyeoldol.starcast.member.domain.*;
import com.mobyeoldol.starcast.member.domain.repository.*;
import com.mobyeoldol.starcast.member.presentation.exception.CustomErrorCode;
import com.mobyeoldol.starcast.member.presentation.exception.CustomException;
import com.mobyeoldol.starcast.member.presentation.request.SaveNicknameRequest;
import com.mobyeoldol.starcast.member.presentation.response.MakeNewNicknameResponse;
import com.mobyeoldol.starcast.member.presentation.response.SaveNicknameResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;


@Slf4j
@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final ProfileRepository profileRepository;
    private final AdjectiveRepository adjectiveRepository;
    private final AdverbRepository adverbRepository;
    private final RandomNicknameRepository randomNicknameRepository;

    @Override
    public MakeNewNicknameResponse makeNewNickname(String bearerToken) {
//        String profileUid = authenticateMember(bearerToken);
        String profileUid = "testProfileId1234";
        Profile profile = profileRepository.findById(profileUid)
                .orElseThrow(()->new CustomException(CustomErrorCode.PROFILE_INFO_NOT_FOUND));

        Random random = new Random();
        random.setSeed(System.currentTimeMillis());
        int advNum = random.nextInt(49) + 1;
        int adjNum = random.nextInt(49) + 1;

        Adjective adjective = adjectiveRepository.findById("ADJ" + adjNum)
                .orElseThrow(()->new CustomException(CustomErrorCode.ADJECTIVE_NOT_FOUND));
        Adverb adverb = adverbRepository.findById("ADV" + advNum)
                .orElseThrow(()->new CustomException(CustomErrorCode.ADVERB_NOT_FOUND));

        Optional<RandomNickname> randomNickname = randomNicknameRepository.findSequenceByAdjectiveAndAdverb(adjective.getAdjectiveUid(), adverb.getAdverbUid());
        int seq = randomNickname.map(nickname -> nickname.getSequence() + 1).orElse(1);

        return MakeNewNicknameResponse.builder()
                .nickname(adjective.getContent() + adverb.getContent() + seq)
                .build();
    }

    @Override
    public SaveNicknameResponse saveNickname(String bearerToken, SaveNicknameRequest request) {
//        String profileUid = authenticateMember(bearerToken);
        String profileUid = "testProfileId1234";
        Profile profile = profileRepository.findById(profileUid)
                .orElseThrow(()->new CustomException(CustomErrorCode.PROFILE_INFO_NOT_FOUND));

        Optional<Profile> updatedProfile = profileRepository.updateNicknameOnProfileById(profile.getProfileUid(), request.getNickname());
        if(updatedProfile.isPresent()) return SaveNicknameResponse.builder().nickname(updatedProfile.get().getNickname()).build();
        else throw new CustomException(CustomErrorCode.SAVE_NICKNAME_FAILED);
    }

    @Override
    public boolean nicknameValidationCheck(SaveNicknameRequest request) {

        boolean result = false;
        String nicknameToCheck = request.getNickname();
        String profileUid;

        if(nicknameToCheck.length() > 20) throw new CustomException(CustomErrorCode.INVALID_NICKNAME_FORMAT);
        else profileUid = profileRepository.findByNickname(nicknameToCheck);

        if(profileUid == null) result = true;

        return result;
    }
}
