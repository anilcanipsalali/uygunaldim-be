package com.uygunaldim.data.dto;

import com.uygunaldim.data.entity.Permission;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class PermissionDto {
    private Long id;
    private String name;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private PermissionRoleDto role;

    public static PermissionDto of(Permission permission) {
        return PermissionDto.builder()
                .id(permission.getId())
                .name(permission.getName())
                .description(permission.getDescription())
                .createdAt(permission.getCreatedAt())
                .updatedAt(permission.getUpdatedAt())
                .role(PermissionRoleDto.of(permission.getRole()))
                .build();
    }
}
