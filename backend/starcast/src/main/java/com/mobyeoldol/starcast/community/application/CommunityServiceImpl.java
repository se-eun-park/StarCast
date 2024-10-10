package com.mobyeoldol.starcast.community.application;

import com.mobyeoldol.starcast.community.presentation.request.ChangeReactionRequest;
import com.mobyeoldol.starcast.community.presentation.request.CreateCommunityListRequest;
import com.mobyeoldol.starcast.community.presentation.request.GetCommunityListRequest;
import com.mobyeoldol.starcast.community.presentation.request.UpdateCommunityRequest;
import com.mobyeoldol.starcast.community.presentation.response.AddressResponse;
import com.mobyeoldol.starcast.community.presentation.response.ChangeReactionResponse;
import com.mobyeoldol.starcast.community.presentation.response.CommunityDetailsResponse;
import com.mobyeoldol.starcast.community.presentation.response.CommunityListResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommunityServiceImpl implements CommunityService {


    @Override
    public void createCommunity(String profileUid, CreateCommunityListRequest request) {

    }

    @Override
    public CommunityListResponse getCommunityList(GetCommunityListRequest request) {
        return null;
    }

    @Override
    public CommunityDetailsResponse getCommunityDetails(String communityUid) {
        return null;
    }

    @Override
    public void updateCommunity(String profileUid, String communityUid, UpdateCommunityRequest request) {

    }

    @Override
    public void deleteCommunity(String profileUid, String communityUid) {

    }

    @Override
    public ChangeReactionResponse changeReaction(String profileUid, String communityUid, ChangeReactionRequest request) {
        return null;
    }

    @Override
    public AddressResponse getAddress(String keyword) {
        return null;
    }
}
