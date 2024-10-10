package com.mobyeoldol.starcast.community.application;

import com.mobyeoldol.starcast.community.domain.Community;
import com.mobyeoldol.starcast.community.domain.CommunityImage;
import com.mobyeoldol.starcast.community.domain.Reaction;
import com.mobyeoldol.starcast.community.domain.enums.SortType;
import com.mobyeoldol.starcast.community.domain.repository.CommunityImageRepository;
import com.mobyeoldol.starcast.community.domain.repository.CommunityRepository;
import com.mobyeoldol.starcast.community.domain.repository.ReactionRepository;
import com.mobyeoldol.starcast.community.presentation.request.ChangeReactionRequest;
import com.mobyeoldol.starcast.community.presentation.request.CreateCommunityListRequest;
import com.mobyeoldol.starcast.community.presentation.request.GetCommunityListRequest;
import com.mobyeoldol.starcast.community.presentation.request.UpdateCommunityRequest;
import com.mobyeoldol.starcast.community.presentation.response.AddressResponse;
import com.mobyeoldol.starcast.community.presentation.response.ChangeReactionResponse;
import com.mobyeoldol.starcast.community.presentation.response.CommunityDetailsResponse;
import com.mobyeoldol.starcast.community.presentation.response.CommunityListResponse;
import com.mobyeoldol.starcast.member.domain.Profile;
import com.mobyeoldol.starcast.member.domain.repository.ProfileRepository;
import com.mobyeoldol.starcast.place.domain.Place;
import com.mobyeoldol.starcast.place.domain.enums.ReactionType;
import com.mobyeoldol.starcast.place.domain.repository.PlaceRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.text.similarity.LevenshteinDistance;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommunityServiceImpl implements CommunityService {
    private final CommunityRepository communityRepository;
    private final CommunityImageRepository communityImageRepository;
    private final PlaceRepository placeRepository;
    private final ProfileRepository profileRepository;
    private final ReactionRepository reactionRepository;

    @Override
    public void createCommunity(String profileUid, CreateCommunityListRequest request) {
        log.info("[관측후기 글 작성 API] POST /api/v1/community/create-community");

        log.info("[관측후기 글 작성 API] 1. 주소와 일치하는 Place 엔티티 찾기");
        CreateCommunityListRequest.Address address = request.getPlace().getAddress();
        Place place = placeRepository.findByAddress1AndAddress2AndAddress3AndAddress4(
                address.getAddress1(),
                address.getAddress2(),
                address.getAddress3(),
                address.getAddress4()
        ).orElseThrow(() -> new EntityNotFoundException("일치하는 장소가 없습니다."));

        log.info("[관측후기 글 작성 API] 2. Profile 찾기");
        log.info("[API] 1. profileUid: {}", profileUid);

        Profile profile = profileRepository.findById(profileUid)
                .orElseThrow(() -> new EntityNotFoundException("해당 프로필을 찾을 수 없습니다."));

        log.info("[관측후기 글 작성 API] 3. 커뮤니티 생성");
        Community community = Community.builder()
                .communityUid(UUID.randomUUID().toString())
                .profile(profile)
                .place(place)
                .title(request.getTitle())
                .content(request.getContent())
                .isDeleted(false)
                .build();
        communityRepository.save(community);

        log.info("[관측후기 글 작성 API] 4. 이미지 저장");
        List<CreateCommunityListRequest.Image> images = request.getImages();
        int seq = 1;
        for (CreateCommunityListRequest.Image image : images) {
            CommunityImage communityImage = CommunityImage.builder()
                    .imageUid(UUID.randomUUID().toString())
                    .community(community)
                    .url(image.getImage())
                    .imageSeq(seq++)
                    .isDeleted(false)
                    .build();
            communityImageRepository.save(communityImage);
        }
    }

    @Override
    public CommunityListResponse getCommunityList(GetCommunityListRequest request) {
        log.info("[관측 후기 전체 조회 API] 1. getCommunityList called with SortType: {}, Option: {}", request.getSortType(), request.getOption());

        List<CommunityListResponse.Communtiy> communityList;

        if (request.getSortType() == SortType.NEWEST) {
            log.info("[관측 후기 전체 조회 API] 2. 최신순으로 정렬된 커뮤니티 목록 가져오기");
            communityList = communityRepository.findByIsDeletedFalseOrderByCreatedDateDesc().stream()
                    .map(this::convertToCommunityResponse)
                    .collect(Collectors.toList());
        } else if (request.getSortType() == SortType.REACTION) {
            log.info("[관측 후기 전체 조회 API] 3. 인기순 (반응 많은 커뮤니티) 목록 가져오기");
            communityList = communityRepository.findTopByReactionType(request.getOption()).stream()
                    .map(this::convertToCommunityResponse)
                    .collect(Collectors.toList());
        } else {
            throw new IllegalArgumentException("유효하지 않은 정렬 방식입니다.");
        }

        return CommunityListResponse.builder()
                .communtiyList(communityList)
                .build();
    }

    // Community 엔티티를 CommunityListResponse.Communtiy로 변환
    private CommunityListResponse.Communtiy convertToCommunityResponse(Community community) {
        // 커뮤니티의 대표 이미지를 가져옴 (첫 번째 이미지)
        String mainImage = community.getCommunityImages().stream()
                .filter(image -> !image.getIsDeleted())
                .sorted(Comparator.comparingInt(CommunityImage::getImageSeq))
                .map(CommunityImage::getUrl)
                .findFirst()
                .orElse(null);

        // 작성자 정보
        Profile profile = community.getProfile();
        CommunityListResponse.Author author = CommunityListResponse.Author.builder()
                .profileUid(profile.getProfileUid())
                .nickname(profile.getNickname())
                .profileImage(profile.getProfileImgNum())
                .build();

        // 장소 정보
        Place place = community.getPlace();
        CommunityListResponse.Place placeResponse = CommunityListResponse.Place.builder()
                .placeUid(place.getPlaceUid())
                .addressSummary(
                        Stream.of(place.getAddress1(), place.getAddress2(), place.getAddress3(), place.getAddress4())
                                .filter(Objects::nonNull)
                                .filter(s -> !s.isEmpty())
                                .collect(Collectors.joining(" ")))
                .build();

        return CommunityListResponse.Communtiy.builder()
                .communityUid(community.getCommunityUid())
                .mainImage(mainImage)
                .title(community.getTitle())
                .author(author)
                .place(placeResponse)
                .dateTime(community.getCreatedDate())
                .build();
    }

    @Override
    public CommunityDetailsResponse getCommunityDetails(String profileUid, String communityUid) {
        log.info("[관측 후기 하나 조회 API] 커뮤니티 UID: {}", communityUid);

        log.info("[관측 후기 하나 조회 API] 1. Community 조회");
        Community community = communityRepository.findById(communityUid)
                .orElseThrow(() -> new EntityNotFoundException("해당 커뮤니티를 찾을 수 없습니다."));

        log.info("[관측 후기 하나 조회 API] 2. 이미지 리스트 조회");
        List<String> images = community.getCommunityImages().stream()
                .filter(image -> !image.getIsDeleted())
                .sorted(Comparator.comparingInt(CommunityImage::getImageSeq))
                .map(CommunityImage::getUrl)
                .collect(Collectors.toList());

        log.info("[관측 후기 하나 조회 API] 3. 작성자 정보 조회 (Profile)");
        Profile authorProfile = community.getProfile();
        CommunityDetailsResponse.Author author = CommunityDetailsResponse.Author.builder()
                .profileUid(authorProfile.getProfileUid())
                .nickname(authorProfile.getNickname())
                .profileImage(authorProfile.getProfileImgNum())
                .build();

        log.info("[관측 후기 하나 조회 API] 4. 장소 정보 조회 (Place)");
        Place place = community.getPlace();
        CommunityDetailsResponse.Place placeResponse = CommunityDetailsResponse.Place.builder()
                .placeUid(place.getPlaceUid())
                .addressSummary(Stream.of(place.getAddress1(), place.getAddress2(), place.getAddress3(), place.getAddress4())
                        .filter(Objects::nonNull)
                        .filter(s -> !s.isEmpty())
                        .collect(Collectors.joining(" ")))
                .build();

        log.info("[관측 후기 하나 조회 API] 5. Reaction 정보 (로그인된 사용자가 누른 반응 조회)");
        boolean visitAgain = community.getReactions().stream()
                .anyMatch(reaction -> reaction.getReactionType() == ReactionType.VISIT_AGAIN &&
                        reaction.getProfile().getProfileUid().equals(profileUid));

        boolean helpful = community.getReactions().stream()
                .anyMatch(reaction -> reaction.getReactionType() == ReactionType.HELPFUL &&
                        reaction.getProfile().getProfileUid().equals(profileUid));

        boolean nicePhotos = community.getReactions().stream()
                .anyMatch(reaction -> reaction.getReactionType() == ReactionType.NICE_PHOTOS &&
                        reaction.getProfile().getProfileUid().equals(profileUid));

        CommunityDetailsResponse.Reaction reactionResponse = CommunityDetailsResponse.Reaction.builder()
                .visitAgain(visitAgain)
                .helpful(helpful)
                .nicePhotos(nicePhotos)
                .build();

        log.info("[관측 후기 하나 조회 API] 6. CommunityDetailsResponse 생성 및 반환");
        return CommunityDetailsResponse.builder()
                .communityUid(community.getCommunityUid())
                .images(images)
                .title(community.getTitle())
                .content(community.getContent())
                .author(author)
                .dateTime(community.getCreatedDate())
                .place(placeResponse)
                .reaction(reactionResponse)
                .build();
    }

    @Override
    public void updateCommunity(String profileUid, String communityUid, UpdateCommunityRequest request) {
//        log.info("[관측후기 글 수정 API] communityUid: {}", communityUid);
//
//        log.info("[관측후기 글 수정 API] 1. communityUid로 커뮤니티 조회");
//        Community community = communityRepository.findById(communityUid)
//                .orElseThrow(() -> new EntityNotFoundException("해당 커뮤니티를 찾을 수 없습니다."));
//
//        log.info("[관측후기 글 수정 API] 2. 작성자 확인");
//        if (!community.getProfile().getProfileUid().equals(profileUid)) {
//            throw new IllegalArgumentException("작성자만 글을 수정할 수 있습니다.");
//        }
//
//        log.info("[관측후기 글 수정 API] 3. 커뮤니티 수정");
//        log.info("[관측후기 글 수정 API] 3-1. 제목이 변경된 경우");
//        if (request.getTitle() != null) {
//            community.setTitle(request.getTitle());
//        }
//
//        log.info("[관측후기 글 수정 API] 3-2. 내용이 변경된 경우");
//        if (request.getContent() != null) {
//            community.setContent(request.getContent());
//        }
//
//        log.info("[관측후기 글 수정 API] 3-3. 장소가 변경된 경우");
//        if (request.getPlace() != null) {
//            Place newPlace = placeRepository.findById(request.getPlace().getPlaceUid())
//                    .orElseThrow(() -> new EntityNotFoundException("해당 장소를 찾을 수 없습니다."));
//            community.setPlace(newPlace);
//        }
//
//        // 4. 이미지 업데이트
//        log.info("[관측후기 글 수정 API] 4. 이미지 업데이트");
//        if (request.getImages() != null && !request.getImages().isEmpty()) {
//            log.info("[관측후기 글 수정 API] 4-1. 기존 이미지 삭제 처리");
//            community.getCommunityImages().forEach(image -> image.setIsDeleted(true));
//
//            // 이미지가 실제로 삭제된 상태를 반영하여 저장
//            communityRepository.saveAndFlush(community); // flush 호출하여 커뮤니티 저장
//
//            log.info("[관측후기 글 수정 API] 4-2. 새로운 이미지 추가");
//            int imageSeq = 1;
//            for (String imageUrl : request.getImages()) {
//                CommunityImage newImage = CommunityImage.builder()
//                        .imageUid(UUID.randomUUID().toString())
//                        .community(community)
//                        .url(imageUrl)
//                        .imageSeq(imageSeq++)
//                        .isDeleted(false)
//                        .build();
//                community.addCommunityImage(newImage);
//            }
//
//            // 이미지 추가 후 다시 저장
//            communityRepository.saveAndFlush(community);
//        }
//
//        // 5. 커뮤니티 최종 저장
//        log.info("[관측후기 글 수정 API] 5. 최종 저장");
//        communityRepository.save(community);
//
//        log.info("[관측후기 글 수정 API] communityUid: {} 수정 완료", communityUid);
    }

    @Override
    public void deleteCommunity(String profileUid, String communityUid) {

        log.info("[관측후기 글 삭제 API] communityUid: {}", communityUid);

        log.info("[관측후기 글 삭제 API] 1. communityUid로 커뮤니티 조회");
        Community community = communityRepository.findById(communityUid)
                .orElseThrow(() -> new EntityNotFoundException("해당 커뮤니티를 찾을 수 없습니다."));

        log.info("[관측후기 글 삭제 API] 2. 작성자 확인");
        if (!community.getProfile().getProfileUid().equals(profileUid)) {
            throw new IllegalArgumentException("작성자만 글을 삭제할 수 있습니다.");
        }

        log.info("[관측후기 글 삭제 API] 3. 삭제 여부를 true로 설정 (논리 삭제)");
        log.info("[관측후기 글 삭제 API] 커뮤니티 삭제 처리");
        community.setIsDeleted(true);

        log.info("[관측후기 글 삭제 API] 4. 저장");
        communityRepository.save(community);

        log.info("[관측후기 글 삭제 API] communityUid: {} 삭제 완료", communityUid);
    }


    @Override
    public ChangeReactionResponse changeReaction(String profileUid, String communityUid, ChangeReactionRequest request) {
        log.info("[관측후기 글에서 반응 클릭시 등록 or 삭제 API] communityUid: {}, reactionType: {}, change: {}",
                communityUid, request.getReaction().getType(), request.getReaction().getChange());

        log.info("[관측후기 글에서 반응 클릭시 등록 or 삭제 API] 1. communityUid로 커뮤니티 조회");
        Community community = communityRepository.findById(communityUid)
                .orElseThrow(() -> new EntityNotFoundException("해당 커뮤니티를 찾을 수 없습니다."));

        log.info("[관측후기 글에서 반응 클릭시 등록 or 삭제 API] 2. profileUid로 사용자(Profile) 조회");
        Profile profile = profileRepository.findById(profileUid)
                .orElseThrow(() -> new EntityNotFoundException("해당 프로필을 찾을 수 없습니다."));

        log.info("[관측후기 글에서 반응 클릭시 등록 or 삭제 API] 3. 리액션 타입에 따라 기존 리액션 조회");
        ReactionType reactionType = request.getReaction().getType();
        Optional<Reaction> existingReaction = reactionRepository.findByProfileAndCommunityAndReactionType(profile, community, reactionType);

        boolean result;

        log.info("[관측후기 글에서 반응 클릭시 등록 or 삭제 API] 4-1 ~ 4-5 중 하나의 로그가 떠야함");
        if (existingReaction.isPresent()) {
            if (request.getReaction().getChange()) {
                log.info("[관측후기 글에서 반응 클릭시 등록 or 삭제 API] 4-1. 리액션이 이미 존재하면 아무 작업도 하지 않음, result는 true");
                result = true;
            } else {
                reactionRepository.delete(existingReaction.get());
                log.info("[관측후기 글에서 반응 API] 4-2. 리액션 삭제 완료");
                result = false;
            }
        } else {
            if (request.getReaction().getChange()) {
                log.info("[관측후기 글에서 반응 클릭시 등록 or 삭제 API] 4-3. 새로운 리액션 추가");
                Reaction newReaction = Reaction.builder()
                        .reactionUid(UUID.randomUUID().toString())
                        .profile(profile)
                        .community(community)
                        .reactionType(reactionType)
                        .build();
                reactionRepository.save(newReaction);
                log.info("[관측후기 글에서 반응 API] 4-4. 리액션 추가 완료");
                result = true;
            } else {
                log.info("[관측후기 글에서 반응 API] 4-5. 삭제할 리액션이 없습니다.");
                result = false;
            }
        }

        log.info("[관측후기 글에서 반응 API] 5. 응답 반환");
        return ChangeReactionResponse.builder()
                .type(reactionType)
                .result(result)
                .build();
    }


    @Override
    public AddressResponse getAddress(String keyword) {
        log.info("[주소 조회 API] keyword: {}", keyword);

        log.info("[주소 조회 API] 1. 키워드 전처리: 공백 제거 및 여러 단어로 나누기");
        String[] keywordParts = keyword.trim().split("\\s+");

        log.info("[주소 조회 API] 2. 모든 Place 데이터 가져오기 (대용량일 경우 성능 고려 필요)");
        List<Place> allPlaces = placeRepository.findAll();

        log.info("[주소 조회 API] 3. 유사도 기반으로 필터링된 Place 목록 생성");
        List<Place> filteredPlaces = allPlaces.stream()
                .filter(place -> isSimilarToKeyword(place, keywordParts))
                .toList();

        log.info("[주소 조회 API] 4. 검색 결과를 AddressResponse로 변환");
        List<AddressResponse.Address> addressList = filteredPlaces.stream()
                .map(place -> AddressResponse.Address.builder()
                        .placeUid(place.getPlaceUid())
                        .address1(place.getAddress1())
                        .address2(place.getAddress2())
                        .address3(place.getAddress3())
                        .address4(place.getAddress4())
                        .addressSummary(formatAddress(place.getAddress1(), place.getAddress2(), place.getAddress3(), place.getAddress4()))
                        .build())
                .collect(Collectors.toList());

        return AddressResponse.builder()
                .addressList(addressList)
                .build();
    }

    // 유사도를 계산하여 필터링하는 메서드
    private boolean isSimilarToKeyword(Place place, String[] keywordParts) {
        LevenshteinDistance levenshtein = new LevenshteinDistance();

        String[] addressFields = {place.getAddress1(), place.getAddress2(), place.getAddress3(), place.getAddress4()};

        log.info("[주소 조회 API] 키워드와 주소 필드 각각에 대해 Levenshtein Distance를 계산");
        for (String keyword : keywordParts) {
            for (String address : addressFields) {
                if (address != null && levenshtein.apply(keyword, address) <= 1) {
                    return true;
                }
            }
        }

        return false;
    }

    private String formatAddress(String address1, String address2, String address3, String address4) {
        return Stream.of(address1, address2, address3, address4)
                .filter(Objects::nonNull)
                .filter(addr -> !addr.isEmpty())
                .collect(Collectors.joining(" "));
    }
}
