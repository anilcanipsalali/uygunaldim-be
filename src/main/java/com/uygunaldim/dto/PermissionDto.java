package com.uygunaldim.dto;

import com.uygunaldim.entity.Permission;
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
    private RoleDto role;

    public static PermissionDto of(Permission permission) {
        return PermissionDto.builder()
                .id(permission.getId())
                .name(permission.getName())
                .description(permission.getDescription())
                .createdAt(permission.getCreatedAt())
                .updatedAt(permission.getUpdatedAt())
                .role(RoleDto.of(permission.getRole()))
                .build();
    }
}
