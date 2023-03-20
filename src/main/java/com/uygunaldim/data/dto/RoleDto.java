package com.uygunaldim.data.dto;

import com.uygunaldim.data.entity.Role;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class RoleDto {
    private Long id;
    private String name;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<RolePermissionDto> permissions;
    private List<RoleUserDto> users;

    public static RoleDto of(Role role) {
        return RoleDto.builder()
                .id(role.getId())
                .name(role.getName())
                .createdAt(role.getCreatedAt())
                .updatedAt(role.getUpdatedAt())
                .permissions(role.getPermissions().stream().map(RolePermissionDto::of).toList())
                .users(role.getUsers().stream().map(RoleUserDto::of).toList())
                .build();
    }
}
