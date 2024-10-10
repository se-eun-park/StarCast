package com.mobyeoldol.starcast.community.presentation.response;

import com.mobyeoldol.starcast.place.domain.enums.ReactionType;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChangeReactionResponse {
    private ReactionType type;
    private boolean result;
}
