package com.uygunaldim.data.dto.request;

import com.uygunaldim.data.dto.PermissionDto;
import com.uygunaldim.data.dto.UserDto;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
@Builder
public class RoleRequest {
    private Long id;
    @NotEmpty(message = "Role name cannot be blank!")
    private String name;
    private List<PermissionDto> permissions;
    private List<UserDto> users;
}
