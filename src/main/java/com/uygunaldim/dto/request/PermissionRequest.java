package com.uygunaldim.dto.request;

import com.uygunaldim.dto.RoleDto;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
public class PermissionRequest {
    private Long id;
    @NotNull(message = "Permission name cannot be null!")
    @NotBlank(message = "Permission name cannot be blank!")
    private String name;
    private String description;
    private RoleDto role;
}
