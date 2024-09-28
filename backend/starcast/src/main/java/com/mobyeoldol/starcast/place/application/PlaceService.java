package com.mobyeoldol.starcast.place.application;

import com.mobyeoldol.starcast.place.domain.FavouriteSpot;
import com.mobyeoldol.starcast.place.presentation.request.CreatePlanRequest;
import com.mobyeoldol.starcast.place.presentation.request.ModifyPlanRequest;
import com.mobyeoldol.starcast.place.presentation.response.PlaceDetailsResponse;
import com.mobyeoldol.starcast.place.presentation.response.PlanDetailsResponse;
import com.mobyeoldol.starcast.place.presentation.response.PlanUidResponse;

public interface PlaceService {
    public FavouriteSpot createFavourite(String placeUid, String profileUid);
    public void deleteFavourite(String spotUid);
    public PlaceDetailsResponse getPlaceDetails(String placeUid);

    public PlanUidResponse makePlan(CreatePlanRequest request, String profileUid);
    public PlanDetailsResponse getPlanDetails(String planUid, String profileUid);
    public PlanDetailsResponse changePlan(ModifyPlanRequest request, String profileUid);
    public void deletePlan(String planUid, String profileUid);
}
