package com.mobyeoldol.starcast.place.presentation.request;

import com.mobyeoldol.starcast.place.domain.enums.PlaceType;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;


@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GetPlaceListRequest {

    @NotNull(message = "sortBy(정렬기준)은 필수입니다.")
    private SortBy sortBy;
    @NotNull(message = "placeType(장소유형)은 필수입니다.")
    private PlaceType placeType;
    private String search;

    @RequiredArgsConstructor
    @Getter
    public static enum SortBy {
        NAME("이름순"),
        REVIEW("리뷰순");

        private final String koreanName;
    }
}
