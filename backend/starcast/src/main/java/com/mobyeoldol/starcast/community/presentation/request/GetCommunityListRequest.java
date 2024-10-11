package com.mobyeoldol.starcast.community.presentation.request;

import com.mobyeoldol.starcast.community.domain.enums.SortType;
import com.mobyeoldol.starcast.place.domain.enums.ReactionType;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GetCommunityListRequest {
    @NotNull(message = "정렬 유형은 필수입니다.")
    private SortType sortType;
    private ReactionType option;
}
