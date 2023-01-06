package com.uygunaldim.dto;

import com.uygunaldim.entity.Role;
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
    private List<PermissionDto> permissions;
    private List<UserDto> users;

    public static RoleDto of(Role role) {
        return RoleDto.builder()
                .id(role.getId())
                .name(role.getName())
                .createdAt(role.getCreatedAt())
                .updatedAt(role.getUpdatedAt())
                .permissions(role.getPermissions().stream().map(PermissionDto::of).toList())
                .users(role.getUsers().stream().map(UserDto::of).toList())
                .build();
    }
}
