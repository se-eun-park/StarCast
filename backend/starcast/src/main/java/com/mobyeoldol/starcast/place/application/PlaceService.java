package com.mobyeoldol.starcast.place.application;

import com.mobyeoldol.starcast.place.domain.FavouriteSpot;
import com.mobyeoldol.starcast.place.domain.enums.MainPlace;
import com.mobyeoldol.starcast.place.presentation.request.CreatePlanRequest;
import com.mobyeoldol.starcast.place.presentation.request.GetPlaceListRequest;
import com.mobyeoldol.starcast.place.presentation.request.ModifyPlanRequest;
import com.mobyeoldol.starcast.place.presentation.response.GetPlaceListResponse;
import com.mobyeoldol.starcast.place.presentation.response.PlaceDetailsResponse;
import com.mobyeoldol.starcast.place.presentation.response.PlanDetailsResponse;
import com.mobyeoldol.starcast.place.presentation.response.PlanUidResponse;

public interface PlaceService {
    FavouriteSpot createFavourite(String placeUid, String profileUid);
    void deleteFavourite(String spotUid);
    PlaceDetailsResponse getPlaceDetails(String placeUid);
    GetPlaceListResponse getPlaceList(GetPlaceListRequest request);

    PlanUidResponse makePlan(CreatePlanRequest request, String profileUid);
    PlanDetailsResponse getPlanDetails(String planUid, String profileUid);
    PlanDetailsResponse changePlan(ModifyPlanRequest request, String profileUid);
    void deletePlan(String planUid, String profileUid);

    void updateActionPlaceType(String profileUid, MainPlace mainPlace);

}
