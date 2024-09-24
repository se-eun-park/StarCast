package com.mobyeoldol.starcast.place.application;

import com.mobyeoldol.starcast.place.domain.Community;
import com.mobyeoldol.starcast.place.domain.CommunityImage;
import com.mobyeoldol.starcast.place.domain.FavouriteSpot;
import com.mobyeoldol.starcast.place.domain.Place;
import com.mobyeoldol.starcast.place.domain.enums.PlaceType;
import com.mobyeoldol.starcast.place.domain.enums.ReactionType;
import com.mobyeoldol.starcast.place.domain.enums.SpotType;
import com.mobyeoldol.starcast.place.domain.repository.CommunityRepository;
import com.mobyeoldol.starcast.place.domain.repository.FavouriteSpotRepository;
import com.mobyeoldol.starcast.place.domain.repository.PlaceRepository;
import com.mobyeoldol.starcast.place.domain.repository.ReactionRepository;
import com.mobyeoldol.starcast.place.presentation.response.PlaceDetailsResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class PlaceServiceImpl implements PlaceService {

    private final PlaceRepository placeRepository;
    private final FavouriteSpotRepository favouriteSpotRepository;
    private final CommunityRepository communityRepository;
    private final ReactionRepository reactionRepository;

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
//                .spotType(SpotType.FAVOURITE)
//                .castarPoint(-1)
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

    public PlaceDetailsResponse getPlaceDetails(String placeUid) {
        log.info("[장소 하나 자세히 보기 API] 1. placeUid 를 이용해 Place 엔티티 조회");
        Optional<Place> optionalCurPlace = placeRepository.findByPlaceUid(placeUid);

        log.info("[장소 하나 자세히 보기 API] \t1-1. 없는 장소일 경우 404 반환");
        if (optionalCurPlace.isEmpty()) {
            throw new IllegalStateException("해당 장소를 찾을 수 없습니다.");
        }
        Place curPlace = optionalCurPlace.get();

        PlaceType curPlaceType = curPlace.getType();
        log.info("[장소 하나 자세히 보기 API] 2. 이름, 장소유형, 주소 조회 / 천문대라면 전화번호, 웹사이트 URL, 이미지 조회 [현재 : " + curPlaceType.getKoreanName() + "]");
        PlaceDetailsResponse.Address curAddress = PlaceDetailsResponse.Address.builder()
                .address1(curPlace.getAddress1())
                .address2(curPlace.getAddress2())
                .address3(curPlace.getAddress3())
                .build();

        String websiteUrl = (curPlaceType == PlaceType.OBSERVATORY) ? curPlace.getWebAddress() : "None";
        String phoneNumber = (curPlaceType == PlaceType.OBSERVATORY) ? curPlace.getPhoneNum() : "None";
        String image = curPlace.getImage() == null? "None" : curPlace.getImage();

        log.info("[장소 하나 자세히 보기 API] 3. 커뮤니티(Community) 테이블과 연관된 리뷰 데이터에서 태그별로 Best 3 리뷰를 조회");
        Map<ReactionType, List<PlaceDetailsResponse.Review>> curTopReviews = findTopReviewsByPlace(curPlace);

        log.info("[장소 하나 자세히 보기 API] 4. 리뷰 수 조회");
        int reviewCount = communityRepository.countByPlace_PlaceUid(placeUid);

        log.info("[장소 하나 자세히 보기 API] 5. PlaceDetailsResponse 생성 및 반환");
        return PlaceDetailsResponse.builder()
                .name(curPlace.getName())
                .type(curPlace.getType())
                .image(image)
                .address(curAddress)
                .websiteUrl(websiteUrl)
                .phoneNumber(phoneNumber)
                .reviewCount(reviewCount)
                .topReviews(curTopReviews)
                .build();
    }

    private Map<ReactionType, List<PlaceDetailsResponse.Review>> findTopReviewsByPlace(Place curPlace) {
        Map<ReactionType, List<PlaceDetailsResponse.Review>> topReviewsMap = new HashMap<>();

        log.info("[장소 하나 자세히 보기 API] \t3-1. 해당 장소에 연결된 모든 Community 가져오기");
        List<Community> communities = communityRepository.findByPlace_PlaceUid(curPlace.getPlaceUid());

        log.info("[장소 하나 자세히 보기 API] \t3-2. 각 ReactionType별로 상위 3개의 Community 찾기");
        for (ReactionType reactionType : ReactionType.values()) {
            List<Community> topCommunities = communities.stream()
                    .sorted((c1, c2) -> Long.compare(
                            reactionRepository.countByCommunity_CommunityUidAndReactionType(c2.getCommunityUid(), reactionType),
                            reactionRepository.countByCommunity_CommunityUidAndReactionType(c1.getCommunityUid(), reactionType)
                    ))
                    .limit(3)
                    .toList();

            log.info("[장소 하나 자세히 보기 API] \t3-3. 각 Community에 대해 Review 생성");
            List<PlaceDetailsResponse.Review> reviews = new ArrayList<>();
            for (Community community : topCommunities) {
                Optional<CommunityImage> communityImage = community.getCommunityImages().stream()
                        .filter(image -> image.getImageSeq() == 1)
                        .findFirst();

                reviews.add(new PlaceDetailsResponse.Review(
                        communityImage.map(CommunityImage::getUrl).orElse(null),
                        community.getTitle(),
                        community.getContent(),
                        community.getCreatedDate().toLocalDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
                ));
            }

            log.info("[장소 하나 자세히 보기 API] \t3-4. ReactionType별로 리뷰 리스트를 Map에 저장");
            topReviewsMap.put(reactionType, reviews);
        }

        return topReviewsMap;
    }
}


