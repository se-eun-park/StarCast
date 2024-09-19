package com.mobyeoldol.starcast.place.application;

import com.mobyeoldol.starcast.place.domain.FavouriteSpot;
import com.mobyeoldol.starcast.place.domain.repository.FavouriteSpotRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class PlaceServiceImpl implements PlaceService {

    private final FavouriteSpotRepository favouriteSpotRepository;

    @Override
    public FavouriteSpot createFavourite(String placeUid, String profileUid) {
        log.info("[즐겨찾기 등록 API] 1. 기존 즐겨찾기 등록 여부 확인");
        Optional<FavouriteSpot> existingFavourite = favouriteSpotRepository.findByPlaceUidAndProfileUid(placeUid, profileUid);

        log.info("[즐겨찾기 등록 API] 1-1. 등록된 장소일 경우 409 반환");
        if (existingFavourite.isPresent()) {
            throw new IllegalStateException("이미 즐겨찾기에 등록된 장소입니다.");
        }

        log.info("[즐겨찾기 등록 API] 2. 유효한 profile_uid와 입력받은 place_uid로 FavouriteSpot 테이블에 새로운 즐겨찾기 항목 생성");
        FavouriteSpot favouriteSpot = FavouriteSpot.builder()
                .spotUid(UUID.randomUUID().toString())
                .placeUid(placeUid)
                .profileUid(profileUid)
                .spotType("FAVOURITE")
                .castarPoint(-1)
                .isDeleted(false)
                .build();

        log.info("[즐겨찾기 등록 API] 3. DB 저장");
        return favouriteSpotRepository.save(favouriteSpot);
    }

    @Override
    public void deleteFavourite(String spotUid) {
        log.info("[즐겨찾기 삭제 API] 1. 기존 즐겨찾기 등록 여부 확인");
        Optional<FavouriteSpot> existingFavourite = favouriteSpotRepository.findById(spotUid);

        log.info("[즐겨찾기 삭제 API] 1-1. 없는 즐겨찾기일 경우 404 반환");
        if (existingFavourite.isEmpty()) {
            throw new IllegalStateException("해당 즐겨찾기 항목을 찾을 수 없습니다.");
        }

        log.info("[즐겨찾기 삭제 API] 2. 즐겨찾기 삭제");
        favouriteSpotRepository.deleteById(spotUid);
    }
}
