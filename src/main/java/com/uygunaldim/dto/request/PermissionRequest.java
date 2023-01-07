package com.uygunaldim.dto.request;

import com.uygunaldim.dto.PermissionRoleDto;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
@Builder
public class PermissionRequest {
    private Long id;
    @NotEmpty(message = "Permission name cannot be blank!")
    private String name;
    @NotEmpty(message = "Permission description cannot be blank!")
    private String description;
    private PermissionRoleDto role;
}
