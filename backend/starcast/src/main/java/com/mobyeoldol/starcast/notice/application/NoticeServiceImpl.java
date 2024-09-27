package com.mobyeoldol.starcast.notice.application;

import com.mobyeoldol.starcast.member.domain.Profile;
import com.mobyeoldol.starcast.member.domain.repository.ProfileRepository;
import com.mobyeoldol.starcast.notice.domain.Consent;
import com.mobyeoldol.starcast.notice.domain.enums.ConsentType;
import com.mobyeoldol.starcast.notice.domain.repository.ConsentRepository;
import com.mobyeoldol.starcast.notice.presentation.response.NoticeResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class NoticeServiceImpl implements NoticeService {

    private final ProfileRepository profileRepository;
    private final ConsentRepository consentRepository;

    @Override
    public NoticeResponse changeNotice(String profileUid, boolean agree) {
        log.info("[알림 수신여부 변경 API] 1. 동의 정보 조회");
        Optional<Consent> optionalConsent = consentRepository.findByProfile_ProfileUidAndType(profileUid, ConsentType.NOTICE);

        if (optionalConsent.isEmpty()) {
            log.error("[알림 수신여부 변경 API] 1-1. 해당 profileUid에 대한 NOTICE 동의 정보가 존재하지 않습니다.");
            throw new IllegalArgumentException("해당 profileUid에 대한 NOTICE 동의 정보가 존재하지 않습니다.");
        }
        Consent consent = optionalConsent.get();

        log.info("[알림 수신여부 변경 API] 2. 동의 여부 업데이트");
        consent.setFlag(agree);
        consentRepository.save(consent);

        log.info("[알림 수신여부 변경 API] 3. 동의 여부가 리턴");
        return NoticeResponse.builder()
                .settingType(consent.isFlag())
                .build();
    }

    private Profile getProfileInfo(String profileUid){
        Optional<Profile> optionalProfile = profileRepository.findById(profileUid);
        if(optionalProfile.isEmpty()) {
            log.error("[getProfileInfo 메서드] 1-2. 해당 프로필 정보를 찾을 수 없습니다.");
            throw new IllegalArgumentException("해당 프로필 정보를 찾을 수 없습니다.");
        }
        return optionalProfile.get();
    }
}
