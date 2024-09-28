package com.mobyeoldol.starcast.place.presentation.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ModifyPlanRequest {
    @NotBlank
    private String planUid;
    private String placeUid;
    private LocalDateTime dateTime;
}
