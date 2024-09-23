package com.mobyeoldol.starcast.member.presentation.request;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SaveNicknameRequest {

    @NotBlank
    private String nickname;
}