package com.uygunaldim.data.dto.request;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
@Builder
public class LoginRequest {
    @NotEmpty(message = "User username cannot be blank!")
    private String username;
    @NotEmpty(message = "User password cannot be blank!")
    private String password;
}
