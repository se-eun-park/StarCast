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
import jakarta.validation.Valid;

public interface PlaceService {
    public FavouriteSpot createFavourite(String placeUid, String profileUid);
    public void deleteFavourite(String spotUid);
    public PlaceDetailsResponse getPlaceDetails(String placeUid);
    public GetPlaceListResponse getPlaceList(GetPlaceListRequest request);

    public PlanUidResponse makePlan(CreatePlanRequest request, String profileUid);
    public PlanDetailsResponse getPlanDetails(String planUid, String profileUid);
    public PlanDetailsResponse changePlan(ModifyPlanRequest request, String profileUid);
    public void deletePlan(String planUid, String profileUid);

    void updateActionPlaceType(String profileUid, MainPlace mainPlace);

}
