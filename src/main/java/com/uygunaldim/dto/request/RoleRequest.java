package com.uygunaldim.dto.request;

import com.uygunaldim.dto.PermissionDto;
import com.uygunaldim.dto.UserDto;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Builder
public class RoleRequest {
    private Long id;
    @NotNull(message = "Role name cannot be null!")
    @NotBlank(message = "Role name cannot be blank!")
    private String name;
    private List<PermissionDto> permissions;
    private List<UserDto> users;
}
