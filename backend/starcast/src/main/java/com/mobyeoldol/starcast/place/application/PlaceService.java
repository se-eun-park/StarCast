package com.mobyeoldol.starcast.place.application;

import com.mobyeoldol.starcast.place.domain.FavouriteSpot;
import com.mobyeoldol.starcast.place.presentation.response.PlaceDetailsResponse;

public interface PlaceService {
    public FavouriteSpot createFavourite(String placeUid, String profileUid);
    public void deleteFavourite(String spotUid);
    public PlaceDetailsResponse getPlaceDetails(String placeUid);
}
