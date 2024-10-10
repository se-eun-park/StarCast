package com.mobyeoldol.starcast.community.presentation;

import com.mobyeoldol.starcast.auth.application.service.AuthService;
import com.mobyeoldol.starcast.community.application.CommunityService;
import com.mobyeoldol.starcast.community.presentation.request.ChangeReactionRequest;
import com.mobyeoldol.starcast.community.presentation.request.CreateCommunityListRequest;
import com.mobyeoldol.starcast.community.presentation.request.GetCommunityListRequest;
import com.mobyeoldol.starcast.community.presentation.request.UpdateCommunityRequest;
import com.mobyeoldol.starcast.community.presentation.response.AddressResponse;
import com.mobyeoldol.starcast.community.presentation.response.ChangeReactionResponse;
import com.mobyeoldol.starcast.community.presentation.response.CommunityDetailsResponse;
import com.mobyeoldol.starcast.community.presentation.response.CommunityListResponse;
import com.mobyeoldol.starcast.global.template.BaseResponseTemplate;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/community")
public class CommunityController {
    private final AuthService authService;
    private final CommunityService communityService;


    @PostMapping("/create-community")
    public ResponseEntity<BaseResponseTemplate<?>> createCommunity(
            @RequestBody CreateCommunityListRequest request,
            BindingResult bindingResult,
            @RequestHeader(value = "Authorization") String bearerToken)
    {
        log.info("[관측후기 글 작성 API] POST /api/v1/community/create-community");
        if (bindingResult.hasErrors()) {
            log.error("[관측후기 글 작성 API] 유효성 검사 실패: {}", bindingResult.getFieldError().getDefaultMessage());
            BaseResponseTemplate<?> errorResponse = BaseResponseTemplate.failure(400, "[관측지 리스트 보기 API] placeType과 sortBy는 필수입니다.");
            return ResponseEntity.status(400).body(errorResponse);
        }

        String profileUid = "profile-uid-01";//authService.authenticateMember(bearerToken);

        log.info("[관측후기 글 작성 API] Service 로직 수행");
        communityService.createCommunity(profileUid, request);

        BaseResponseTemplate<?> successResponse = BaseResponseTemplate.success("관측후기 글이 성공적으로 업로드되었습니다.");
        return ResponseEntity.ok().body(successResponse);
    }

    @GetMapping
    public ResponseEntity<BaseResponseTemplate<?>> getCommunityList(
            @Valid @RequestBody GetCommunityListRequest request,
            BindingResult bindingResult,
            @RequestHeader(value = "Authorization") String bearerToken) {

        log.info("[관측 후기 전체 조회 API] GET /api/v1/community");
        if (bindingResult.hasErrors()) {
            log.error("[관측 후기 전체 조회 API] 유효성 검사 실패: {}", bindingResult.getFieldError().getDefaultMessage());
            BaseResponseTemplate<?> errorResponse = BaseResponseTemplate.failure(400, "[관측 후기 전체 조회 API] sortType과 option을 확인해주세요.");
            return ResponseEntity.status(400).body(errorResponse);
        }

        String profileUid = "profile-uid-01";//authService.authenticateMember(bearerToken);

        log.info("[관측 후기 전체 조회 API] Service 로직 수행");
        CommunityListResponse response = communityService.getCommunityList(request);

        BaseResponseTemplate<CommunityListResponse> successResponse = BaseResponseTemplate.success(response);
        return ResponseEntity.ok().body(successResponse);
    }

    @GetMapping("/{community_uid}")
    public ResponseEntity<BaseResponseTemplate<?>> getCommunityDetails(
            @PathVariable(value = "community_uid") String communityUid,
            @RequestHeader(value = "Authorization") String bearerToken)
    {
        log.info("[관측 후기 하나 조회 API] GET /api/v1/community/{}", communityUid);

        String profileUid = "profile-uid-01";//authService.authenticateMember(bearerToken);

        log.info("[관측 후기 하나 조회 API] Service 로직 수행");
        CommunityDetailsResponse response = communityService.getCommunityDetails(profileUid, communityUid);

        BaseResponseTemplate<CommunityDetailsResponse> successResponse = BaseResponseTemplate.success(response);
        return ResponseEntity.ok().body(successResponse);
    }

    @PatchMapping("/{community_uid}")
    public ResponseEntity<BaseResponseTemplate<?>> updateCommunity(
            @PathVariable(value = "community_uid") String communityUid,
            @RequestBody UpdateCommunityRequest request,
            BindingResult bindingResult,
            @RequestHeader(value = "Authorization") String bearerToken)
    {
        log.info("[관측후기 글 수정 API] PATCH /api/v1/community/{}",communityUid);
        if (bindingResult.hasErrors()) {
            log.error("[관측후기 글 수정 API] 유효성 검사 실패: {}", bindingResult.getFieldError().getDefaultMessage());
            BaseResponseTemplate<?> errorResponse = BaseResponseTemplate.failure(400, "[관측지 리스트 보기 API] placeType과 sortBy는 필수입니다.");
            return ResponseEntity.status(400).body(errorResponse);
        }

        String profileUid = "profile-uid-01";//authService.authenticateMember(bearerToken);

        log.info("[관측후기 글 수정 API] Service 로직 수행");
        communityService.updateCommunity(profileUid, communityUid, request);

        BaseResponseTemplate<?> successResponse = BaseResponseTemplate.success("관측후기 글이 성공적으로 업데이트되었습니다.");
        return ResponseEntity.ok().body(successResponse);
    }

    @DeleteMapping("/{community_uid}")
    public ResponseEntity<BaseResponseTemplate<?>> deleteCommunity(
            @PathVariable(value = "community_uid") String communityUid,
            @RequestHeader(value = "Authorization") String bearerToken)
    {
        log.info("[관측후기 글 삭제 API] DELETE /api/v1/community/{}",communityUid);

        String profileUid = "profile-uid-01";//authService.authenticateMember(bearerToken);

        log.info("[관측후기 글 삭제 API] Service 로직 수행");
        communityService.deleteCommunity(profileUid, communityUid);

        BaseResponseTemplate<?> successResponse = BaseResponseTemplate.success(null);
        return ResponseEntity.ok().body(successResponse);
    }

    @PostMapping("/{community_uid}")
    public ResponseEntity<BaseResponseTemplate<?>> changeReaction(
            @PathVariable(value = "community_uid") String communityUid,
            @RequestBody ChangeReactionRequest request,
            BindingResult bindingResult,
            @RequestHeader(value = "Authorization") String bearerToken)
    {
        log.info("[관측후기 글에서 반응 클릭시 등록 or 삭제 API] POST /api/v1/community/{}", communityUid);
        if (bindingResult.hasErrors()) {
            log.error("[관측후기 글에서 반응 클릭시 등록 or 삭제 API] 유효성 검사 실패: {}", bindingResult.getFieldError().getDefaultMessage());
            BaseResponseTemplate<?> errorResponse = BaseResponseTemplate.failure(400, "[관측지 리스트 보기 API] placeType과 sortBy는 필수입니다.");
            return ResponseEntity.status(400).body(errorResponse);
        }

        String profileUid = "profile-uid-01";//authService.authenticateMember(bearerToken);

        log.info("[관측후기 글에서 반응 클릭시 등록 or 삭제 API] Service 로직 수행");
        ChangeReactionResponse response = communityService.changeReaction(profileUid, communityUid, request);

        BaseResponseTemplate<?> successResponse = BaseResponseTemplate.success(response);
        return ResponseEntity.ok().body(successResponse);
    }

    @GetMapping(params = "keyword")
    public ResponseEntity<BaseResponseTemplate<?>> getAddress(
            @RequestParam String keyword,
            @RequestHeader(value = "Authorization") String bearerToken)
    {
        log.info("[주소 조회 API] GET /api/v1/community?keyword={}", keyword);

        String profileUid = "profile-uid-01";//authService.authenticateMember(bearerToken);

        log.info("[주소 조회 API] Service 로직 수행");
        AddressResponse response = communityService.getAddress(keyword);

        BaseResponseTemplate<?> successResponse = BaseResponseTemplate.success(response);
        return ResponseEntity.ok().body(successResponse);
    }
}

