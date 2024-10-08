package com.mobyeoldol.starcast.community.application;

import com.mobyeoldol.starcast.community.presentation.request.ChangeReactionRequest;
import com.mobyeoldol.starcast.community.presentation.request.CreateCommunityListRequest;
import com.mobyeoldol.starcast.community.presentation.request.GetCommunityListRequest;
import com.mobyeoldol.starcast.community.presentation.request.UpdateCommunityRequest;
import com.mobyeoldol.starcast.community.presentation.response.AddressResponse;
import com.mobyeoldol.starcast.community.presentation.response.ChangeReactionResponse;
import com.mobyeoldol.starcast.community.presentation.response.CommunityDetailsResponse;
import com.mobyeoldol.starcast.community.presentation.response.CommunityListResponse;

public interface CommunityService {
    void createCommunity(String profileUid, CreateCommunityListRequest request);

    CommunityListResponse getCommunityList(GetCommunityListRequest request);

    CommunityDetailsResponse getCommunityDetails(String communityUid);

    void updateCommunity(String profileUid, String communityUid, UpdateCommunityRequest request);

    void deleteCommunity(String profileUid, String communityUid);

    ChangeReactionResponse changeReaction(String profileUid, String communityUid, ChangeReactionRequest request);

    AddressResponse getAddress(String keyword);
}
