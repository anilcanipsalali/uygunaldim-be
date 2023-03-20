package com.uygunaldim.data.dto;

import com.uygunaldim.data.entity.Role;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class UserRoleDto {
    private Long id;
    private String name;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<RolePermissionDto> permissions;

    public static UserRoleDto of(Role role) {
        return UserRoleDto.builder()
                .id(role.getId())
                .name(role.getName())
                .createdAt(role.getCreatedAt())
                .updatedAt(role.getUpdatedAt())
                .permissions(role.getPermissions().stream().map(RolePermissionDto::of).toList())
                .build();
    }
}
