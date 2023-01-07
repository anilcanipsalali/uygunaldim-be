package com.uygunaldim.dto.request;

import com.uygunaldim.dto.UserRoleDto;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Builder
public class UserRequest {
    private Long id;
    @NotEmpty(message = "User email cannot be blank!")
    private String email;
    @NotEmpty(message = "User username cannot be blank!")
    private String username;
    @NotEmpty(message = "User password cannot be blank!")
    private String password;
    @NotNull(message = "User role cannot be blank!")
    private UserRoleDto role;
}
