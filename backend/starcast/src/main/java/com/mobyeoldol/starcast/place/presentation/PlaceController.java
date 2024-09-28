package com.mobyeoldol.starcast.place.presentation;

import com.mobyeoldol.starcast.place.application.PlaceServiceImpl;
import com.mobyeoldol.starcast.place.domain.FavouriteSpot;
import com.mobyeoldol.starcast.place.domain.enums.MainPlace;
import com.mobyeoldol.starcast.place.presentation.response.FavouriteSpotResponse;
import com.mobyeoldol.starcast.place.presentation.response.PlaceDetailsResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/place")
public class PlaceController {

    private final PlaceServiceImpl placeService;

    @PostMapping("/{place_uid}/favourite")
    public ResponseEntity<?> createFavourite(
            @PathVariable(value = "place_uid") String placeUid,
            @RequestHeader(value = "Authorization") String bearerToken
    ){
        log.info("[즐겨찾기 등록 API] POST /api/v1/place/{place_uid}/favourite");
        String profileUid = ""; // authenticateMember(bearerToken);

        try {
            log.info("[즐겨찾기 등록 API] 즐겨찾기 등록 Service 로직 수행");
            FavouriteSpot favouriteSpot = placeService.createFavourite(placeUid, profileUid);

            log.info("[즐겨찾기 등록 API] 정상 처리되어 201 반환");
            FavouriteSpotResponse responseDto = new FavouriteSpotResponse(favouriteSpot.getSpotUid());
            return ResponseEntity.status(201).body(responseDto);
        } catch (IllegalStateException e) {
            log.info("[즐겨찾기 등록 API] 즐겨찾기가 이미 등록되어 409 반환");
            return ResponseEntity.status(409).body(e.getMessage());
        }

    }

    @DeleteMapping("/favourite/{spot_uid}")
    public ResponseEntity<?> deleteFavourite(
            @PathVariable(value = "spot_uid") String spotUid,
            @RequestHeader(value = "Authorization") String bearerToken
    ){
        log.info("[즐겨찾기 삭제 API] DELETE /api/v1/place/favourite/{spot_uid}");
        String profileUid = ""; // authenticateMember(bearerToken);

        try {
            log.info("[즐겨찾기 삭제 API] 즐겨찾기 삭제 Service 로직 수행");
            placeService.deleteFavourite(spotUid);
            return ResponseEntity.ok().body("즐겨찾기가 성공적으로 삭제되었습니다.");
        } catch (IllegalArgumentException e) {
            log.info("[즐겨찾기 삭제 API] 즐겨찾기 항목을 찾을 수 없는 경우 404 반환");
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }


    @GetMapping("/{place_uid}")
    public ResponseEntity<?> getPlaceDetails(
            @PathVariable(value = "place_uid") String placeUid,
            @RequestHeader(value = "Authorization") String bearerToken
    ){
        log.info("[장소 하나 자세히 보기 API] GET /api/v1/place/{place_uid}");
        String profileUid = ""; // authenticateMember(bearerToken);

        try {
            log.info("[장소 하나 자세히 보기 API] 장소 조회 Service 로직 수행");
            PlaceDetailsResponse placeDetailsResponse = placeService.getPlaceDetails(placeUid);
            return ResponseEntity.ok().body(placeDetailsResponse);
        } catch (IllegalArgumentException e) {
            log.info("[장소 하나 자세히 보기 API] 장소를 찾을 수 없는 경우 404 반환");
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }


    @PatchMapping("/update-action/{place_type}")
    public ResponseEntity<?> updateFavourite(
            @PathVariable(value = "place_type") MainPlace mainPlace,
            @RequestHeader(value = "Authorization") String bearerToken)
    {
        log.info("[메인 장소 유형 업데이트 API] PATCH /api/v1/place/update-action/{}", mainPlace);
        String profileUid = ""; // authenticateMember(bearerToken);

        log.info("[메인 장소 유형 업데이트 API] Service 로직 수행");
        placeService.updateActionPlaceType(profileUid, mainPlace);

        return ResponseEntity.ok().build();
    }
}
