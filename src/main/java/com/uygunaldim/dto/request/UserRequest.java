package com.uygunaldim.dto.request;

import com.uygunaldim.dto.RoleDto;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
public class UserRequest {
    private Long id;
    @NotNull(message = "User email cannot be null!")
    @NotBlank(message = "User email cannot be blank!")
    private String email;
    @NotNull(message = "User username cannot be null!")
    @NotBlank(message = "User username cannot be blank!")
    private String username;
    @NotNull(message = "User password cannot be null!")
    @NotBlank(message = "User password cannot be blank!")
    private String password;
    @NotNull(message = "User role cannot be null!")
    @NotBlank(message = "User role cannot be blank!")
    private RoleDto role;
}
