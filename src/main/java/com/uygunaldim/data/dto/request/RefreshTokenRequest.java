package com.uygunaldim.data.dto.request;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
public class RefreshTokenRequest {
    @NotNull(message = "User ID cannot be null!")
    @Min(0)
    private Long userId;
    @NotNull(message = "Refresh token cannot be null!")
    @NotBlank(message = "Refresh token cannot be blank!")
    private String refreshToken;
}
