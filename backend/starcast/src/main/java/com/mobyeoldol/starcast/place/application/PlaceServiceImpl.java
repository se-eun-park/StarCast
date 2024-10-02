package com.mobyeoldol.starcast.place.application;

import com.mobyeoldol.starcast.community.domain.Community;
import com.mobyeoldol.starcast.community.domain.CommunityImage;
import com.mobyeoldol.starcast.community.domain.repository.CommunityRepository;
import com.mobyeoldol.starcast.community.domain.repository.ReactionRepository;
import com.mobyeoldol.starcast.member.domain.Profile;
import com.mobyeoldol.starcast.member.domain.repository.ProfileRepository;
import com.mobyeoldol.starcast.place.domain.FavouriteSpot;
import com.mobyeoldol.starcast.place.domain.Place;
import com.mobyeoldol.starcast.place.domain.enums.MainPlace;
import com.mobyeoldol.starcast.place.domain.Plan;
import com.mobyeoldol.starcast.place.domain.enums.PlaceType;
import com.mobyeoldol.starcast.place.domain.enums.ReactionType;
import com.mobyeoldol.starcast.place.domain.repository.FavouriteSpotRepository;
import com.mobyeoldol.starcast.place.domain.repository.PlaceRepository;
import com.mobyeoldol.starcast.place.domain.repository.PlanRepository;
import com.mobyeoldol.starcast.place.presentation.request.CreatePlanRequest;
import com.mobyeoldol.starcast.place.presentation.request.GetPlaceListRequest;
import com.mobyeoldol.starcast.place.presentation.request.ModifyPlanRequest;
import com.mobyeoldol.starcast.place.presentation.response.GetPlaceListResponse;
import com.mobyeoldol.starcast.place.presentation.response.PlaceDetailsResponse;
import com.mobyeoldol.starcast.place.presentation.response.PlanDetailsResponse;
import com.mobyeoldol.starcast.place.presentation.response.PlanUidResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.text.similarity.LevenshteinDistance;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class PlaceServiceImpl implements PlaceService {

    private final PlaceRepository placeRepository;
    private final FavouriteSpotRepository favouriteSpotRepository;
    private final PlanRepository planRepository;
    private final CommunityRepository communityRepository;
    private final ReactionRepository reactionRepository;
    private final ProfileRepository profileRepository;

    private final LevenshteinDistance levenshtein = new LevenshteinDistance();

    @Transactional
    @Override
    public FavouriteSpot createFavourite(String placeUid, String profileUid) {
        log.info("[즐겨찾기 등록 API] 1. 기존 즐겨찾기 등록 여부 확인");
        Optional<FavouriteSpot> existingFavourite = favouriteSpotRepository.findByPlace_PlaceUidAndProfile_ProfileUid(placeUid, profileUid);

        if (existingFavourite.isPresent()) {
            throw new IllegalStateException("[즐겨찾기 등록 API] 1-1. 이미 즐겨찾기에 등록된 장소입니다.");
        }

        log.info("[즐겨찾기 등록 API] 2. 유효한 profile_uid와 입력받은 place_uid로 FavouriteSpot 테이블에 새로운 즐겨찾기 항목 생성");
        Profile profile = profileRepository.findById(profileUid)
                .orElseThrow(() -> new IllegalArgumentException("[즐겨찾기 등록 API] 2-1. 해당 프로필 정보를 찾을 수 없습니다."));

        Place place = placeRepository.findById(placeUid)
                .orElseThrow(() -> new IllegalStateException("[즐겨찾기 등록 API] 2-2. 해당 장소를 찾을 수 없습니다."));

        FavouriteSpot favouriteSpot = FavouriteSpot.builder()
                .spotUid(UUID.randomUUID().toString())
                .profile(profile)
                .place(place)
                .isDeleted(false)
                .build();

        log.info("[즐겨찾기 등록 API] 3. DB 저장");
        return favouriteSpotRepository.save(favouriteSpot);
    }

    @Transactional
    @Override
    public void deleteFavourite(String spotUid) {
        log.info("[즐겨찾기 삭제 API] 1. 기존 즐겨찾기 등록 여부 확인");
        FavouriteSpot favouriteSpot = favouriteSpotRepository.findById(spotUid)
                .orElseThrow(() -> new IllegalStateException("[즐겨찾기 삭제 API] 1-1. 해당 즐겨찾기 항목을 찾을 수 없습니다."));

        log.info("[즐겨찾기 삭제 API] 2. 즐겨찾기 삭제");
        favouriteSpotRepository.deleteById(spotUid);
    }

    @Transactional(readOnly = true)
    @Override
    public PlaceDetailsResponse getPlaceDetails(String placeUid) {
        log.info("[장소 하나 자세히 보기 API] 1. placeUid 를 이용해 Place 엔티티 조회");
        Place curPlace = placeRepository.findByPlaceUid(placeUid)
                .orElseThrow(() -> new IllegalStateException("[장소 하나 자세히 보기 API] 1-1. 해당 장소를 찾을 수 없습니다."));

        PlaceType curPlaceType = curPlace.getType();
        log.info("[장소 하나 자세히 보기 API] 2. 이름, 장소유형, 주소 조회 / 천문대라면 전화번호, 웹사이트 URL, 이미지 조회 [현재 : " + curPlaceType.getKoreanName() + "]");
        PlaceDetailsResponse.Address curAddress = PlaceDetailsResponse.Address.builder()
                .address1(curPlace.getAddress1())
                .address2(curPlace.getAddress2())
                .address3(curPlace.getAddress3())
                .address4(curPlace.getAddress4()==null?"":curPlace.getAddress4())
                .build();

        String websiteUrl = (curPlaceType == PlaceType.OBSERVATORY) ? curPlace.getWebAddress() : "None";
        String phoneNumber = (curPlaceType == PlaceType.OBSERVATORY) ? curPlace.getPhoneNum() : "None";
        String image = Optional.ofNullable(curPlace.getImage()).orElse("None");

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

    @Override
    public GetPlaceListResponse getPlaceList(GetPlaceListRequest request) {

        log.info("[관측지 리스트 보기 API] 1. 검색어 입력 확인");
        if (request.getSearch() != null && !request.getSearch().isEmpty()) {
            log.info("[관측지 리스트 보기 API] \t1-1. 검색어가 있는 경우, 유사도 알고리즘 사용");
            return searchWithSimilarityAlgorithm(request);
        }

        log.info("[관측지 리스트 보기 API] 2. 정렬 기준에 따라 처리");
        List<Place> places = placeRepository.findByType(request.getPlaceType());

        log.info("[관측지 리스트 보기 API] \t2-1. 이름 순 정렬");
        if (request.getSortBy() == GetPlaceListRequest.SortBy.NAME) {
            places.sort(Comparator.comparing(Place::getName));
        }

        log.info("[관측지 리스트 보기 API] \t2-1. 리뷰 순 정렬");
        if (request.getSortBy() == GetPlaceListRequest.SortBy.REVIEW) {
            places.sort(Comparator.comparingInt(place -> place.getCommunities().size()));
        }

        log.info("[관측지 리스트 보기 API] 3. 응답 데이터 생성");
        List<GetPlaceListResponse.Data> dataList = places.stream().map(this::mapPlaceToData).collect(Collectors.toList());
        return new GetPlaceListResponse(dataList);
    }

    @Transactional
    @Override
    public PlanUidResponse makePlan(CreatePlanRequest request, String profileUid) {
        log.info("[장소 찜 생성 API] 1. Profile 정보 조회");
        Profile curProfile = profileRepository.findById(profileUid)
                .orElseThrow(() -> new IllegalArgumentException("[장소 찜 생성 API] 1-1. 해당 프로필 정보를 찾을 수 없습니다."));

        log.info("[장소 찜 생성 API] 2. 입력받은 장소 아이디가 유효한지 확인");
        Place curPlace = placeRepository.findByPlaceUid(request.getPlaceUid())
                .orElseThrow(() -> new IllegalStateException("[장소 찜 생성 API] 2-1. 해당 장소를 찾을 수 없습니다."));

        log.info("[장소 찜 생성 API] 3. 이미 존재하는 찜 삭제");
        planRepository.findByPlace_PlaceUidAndProfile_ProfileUid(request.getPlaceUid(), profileUid)
                .ifPresent(planRepository::delete);

        log.info("[장소 찜 생성 API] 4. 새로운 찜 엔티티 생성 및 저장");
        Plan plan = Plan.builder()
                .planUid(UUID.randomUUID().toString())
                .profile(curProfile)
                .place(curPlace)
                .dateTime(request.getDateTime())
                .castarPoint(-1) // TODO: 캐스타 점수 계산
                .isDeleted(false)
                .build();
        planRepository.save(plan);

        log.info("[장소 찜 생성 API] 5. 응답 반환");
        return new PlanUidResponse(plan.getPlanUid());
    }

    @Transactional
    @Override
    public PlanDetailsResponse getPlanDetails(String planUid, String profileUid) {
        log.info("[장소 찜 조회 API] 1. Plan 유효한지 검증");
        Plan plan = planRepository.findById(planUid)
                .orElseThrow(() -> new IllegalStateException("[장소 찜 조회 API] 1-1. 해당 찜을 찾을 수 없습니다."));

        log.info("[장소 찜 조회 API] 비교 : "+profileUid+", "+plan.getProfile().getProfileUid());

        log.info("[장소 찜 조회 API] 2. Profile 일치 여부 확인");
        if (!profileUid.equals(plan.getProfile().getProfileUid())) {
            throw new IllegalStateException("[장소 찜 조회 API] 2-1. 찜을 조회할 권한이 없습니다.");
        }

        log.info("[장소 찜 조회 API] 3. 응답 생성 및 반환");
        return makePlanDetailsResponse(plan.getPlace(), plan);
    }

    @Transactional
    @Override
    public PlanDetailsResponse changePlan(ModifyPlanRequest request, String profileUid) {
        log.info("[장소 찜 수정 API] 1. Plan 유효한지 검증");
        Plan plan = planRepository.findById(request.getPlanUid())
                .orElseThrow(() -> new IllegalStateException("[장소 찜 수정 API] 1-1. 해당 찜을 찾을 수 없습니다."));

        log.info("[장소 찜 수정 API] 2. Profile 일치 여부 확인");
        if (!profileUid.equals(plan.getProfile().getProfileUid())) {
            throw new IllegalStateException("[장소 찜 수정 API] 2-1. 찜을 수정할 권한이 없습니다.");
        }

        log.info("[장소 찜 수정 API] 3. 입력 값에 따라 Plan 수정하기");
        if (request.getPlaceUid() != null) {
            log.info("[장소 찜 수정 API] 3-1. 장소 정보 수정");
            Place newPlace = placeRepository.findByPlaceUid(request.getPlaceUid())
                    .orElseThrow(() -> new IllegalStateException("[장소 찜 수정 API] 3-1-1. 해당 장소를 찾을 수 없습니다."));
            plan.setPlace(newPlace);
        }
        if (request.getDateTime() != null) {
            log.info("[장소 찜 수정 API] 3-2. 날짜 정보 수정");
            plan.setDateTime(request.getDateTime());
        }

        log.info("[장소 찜 수정 API] 4. 최종 저장 및 응답 반환");
        planRepository.save(plan);
        return makePlanDetailsResponse(plan.getPlace(), plan);
    }

    @Transactional
    @Override
    public void deletePlan(String planUid, String profileUid) {
        log.info("[장소 찜 삭제 API] 1. Plan 조회 및 검증");
        Plan plan = planRepository.findById(planUid)
                .orElseThrow(() -> new IllegalStateException("[장소 찜 삭제 API] 1-1. 해당 찜을 찾을 수 없습니다."));

        log.info("[찜 삭제] 비교 : "+profileUid+", "+plan.getProfile().getProfileUid());

        log.info("[장소 찜 삭제 API] 2. Profile 일치 여부 확인");
        if (!profileUid.equals(plan.getProfile().getProfileUid())) {
            throw new IllegalStateException("[장소 찜 삭제 API] 2-1. 찜을 삭제할 권한이 없습니다.");
        }

        log.info("[장소 찜 삭제 API] 3. 이미 삭제된 찜인지 확인");
        if (plan.getIsDeleted()) {
            throw new IllegalStateException("[장소 찜 삭제 API] 3-1. 이미 삭제된 찜입니다.");
        }

        log.info("[장소 찜 삭제 API] 4. Plan 삭제 처리");
        plan.setIsDeleted(true);
        planRepository.save(plan);
    }


    @Override
    public void updateActionPlaceType(String profileUid, MainPlace mainPlace) {
        profileUid = "profile1";
        log.info("[메인 장소 유형 업데이트 API] 1. 프로필 조회");
        Profile profile = profileRepository.findById(profileUid)
                .orElseThrow(() -> new IllegalArgumentException("[메인 장소 유형 업데이트 API] 1-1. 해당 프로필 정보를 찾을 수 없습니다."));

        log.info("[메인 장소 유형 업데이트 API] 2. 메인 클릭한 장소 유형 업데이트");
        profile.setActionPlaceType(mainPlace.name());
        profileRepository.save(profile);
    }

    private Map<ReactionType, List<PlaceDetailsResponse.Review>> findTopReviewsByPlace(Place curPlace) {
        Map<ReactionType, List<PlaceDetailsResponse.Review>> topReviewsMap = new HashMap<>();

        log.info("[장소 하나 자세히 보기 API] 3-1. 해당 장소에 연결된 모든 Community 가져오기");
        List<Community> communities = communityRepository.findByPlace_PlaceUid(curPlace.getPlaceUid());

        log.info("[장소 하나 자세히 보기 API] 3-2. 각 ReactionType별로 상위 3개의 Community 찾기");
        for (ReactionType reactionType : ReactionType.values()) {
            List<Community> topCommunities = communities.stream()
                    .sorted((c1, c2) -> Long.compare(
                            reactionRepository.countByCommunity_CommunityUidAndReactionType(c2.getCommunityUid(), reactionType),
                            reactionRepository.countByCommunity_CommunityUidAndReactionType(c1.getCommunityUid(), reactionType)
                    ))
                    .limit(3)
                    .toList();

            log.info("[장소 하나 자세히 보기 API] 3-3. 각 Community에 대해 Review 생성");
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

            log.info("[장소 하나 자세히 보기 API] 3-4. ReactionType별로 리뷰 리스트를 Map에 저장");
            topReviewsMap.put(reactionType, reviews);
        }

        return topReviewsMap;
    }

    private PlanDetailsResponse makePlanDetailsResponse(Place curPlace, Plan plan) {
        PlanDetailsResponse.Address address = PlanDetailsResponse.Address.builder()
                .address1(curPlace.getAddress1())
                .address2(curPlace.getAddress2())
                .address3(curPlace.getAddress3())
                .address4(curPlace.getAddress4())
                .build();

        PlanDetailsResponse.Place place = PlanDetailsResponse.Place.builder()
                .placeUid(curPlace.getPlaceUid())
                .name(curPlace.getName())
                .type(curPlace.getType())
                .image(curPlace.getImage())
                .address(address)
                .build();

        return PlanDetailsResponse.builder()
                .planUid(plan.getPlanUid())
                .place(place)
                .dateTime(plan.getDateTime())
                .castarPoint(plan.getCastarPoint())
                .isDeleted(plan.getIsDeleted())
                .build();
    }


    private GetPlaceListResponse searchWithSimilarityAlgorithm(GetPlaceListRequest request) {
        String keyword = request.getSearch();
        PlaceType type = request.getPlaceType();

        log.info("[관측지 리스트 보기 API] 공백만 있거나 특수문자가 포함된 경우 검색결과는 빈 리스트");
        if (keyword == null || keyword.trim().isEmpty() || keyword.matches("[\\p{Punct}\\s]*")) {
            return new GetPlaceListResponse(Collections.emptyList());
        }

        List<Place> allPlaces = placeRepository.findByType(type);

        log.info("[관측지 리스트 보기 API] Levenshtein 거리 계산");
        List<Place> similarPlaces = allPlaces.stream()
                .filter(place -> {
                    double similarity = calculateSimilarity(place, keyword);
                    return similarity > 0.95;
                })
                .sorted(Comparator.comparingDouble(place -> calculateSimilarity(place, keyword)))
                .toList();

        log.info("[관측지 리스트 보기 API] 응답 데이터 생성");
        List<GetPlaceListResponse.Data> dataList = similarPlaces.stream().map(this::mapPlaceToData).collect(Collectors.toList());
        return new GetPlaceListResponse(dataList);
    }

    private double calculateSimilarity(Place place, String keyword) {
        log.info("[관측지 리스트 보기 API] 장소 이름과 주소들에 대해 유사도 계산");
        String combinedString = String.join(" ", place.getName(), place.getAddress1(), place.getAddress2(), place.getAddress3(), place.getAddress4(), place.getPhoneNum());

        log.info("[관측지 리스트 보기 API] Levenshtein 거리 계산");
        return levenshtein.apply(combinedString, keyword);
    }

    private GetPlaceListResponse.Data mapPlaceToData(Place place) {
        log.info("[관측지 리스트 보기 API] Place를 GetPlaceListResponse.Data로 변환");
        return new GetPlaceListResponse.Data(
                place.getPlaceUid(),
                place.getImage(),
                place.getName(),
                new GetPlaceListResponse.Address(
                        place.getAddress1(),
                        place.getAddress2(),
                        place.getAddress3(),
                        place.getAddress4()
                ),
                placeRepository.countCommunitiesByPlace(place)
        );
    }

}