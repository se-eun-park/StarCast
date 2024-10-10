package com.mobyeoldol.starcast.community.presentation.request;

import com.mobyeoldol.starcast.place.domain.enums.ReactionType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ChangeReactionRequest {
    @NotBlank(message = "커뮤니티 UID는 필수입니다.")
    private String communityUid;
    @NotNull(message = "리액션 정보는 필수입니다.")
    private Reaction reaction;

    class Reaction {
        @NotNull(message = "리액션 타입은 필수입니다.")
        private ReactionType type;
        @NotNull(message = "리액션 변경 여부는 필수입니다.")
        private Boolean change;
    }
}
