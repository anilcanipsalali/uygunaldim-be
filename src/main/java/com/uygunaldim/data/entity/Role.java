package com.uygunaldim.data.entity;

import com.uygunaldim.data.dto.PermissionRoleDto;
import com.uygunaldim.data.dto.RoleDto;
import com.uygunaldim.data.dto.UserRoleDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "ROLE")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Role {
    @Id
    @SequenceGenerator(name = "seqRoleId", sequenceName = "seq_role_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqRoleId")
    private Long id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "CREATED_AT")
    private LocalDateTime createdAt;
    @Column(name = "UPDATED_AT")
    private LocalDateTime updatedAt;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "permission_id", referencedColumnName = "id")
    private List<Permission> permissions;
    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL)
    private List<User> users;

    public static Role of(RoleDto roleDto) {
        return Role.builder()
                .id(roleDto.getId())
                .name(roleDto.getName())
                .permissions(roleDto.getPermissions().stream().map(Permission::of).toList())
                .users(roleDto.getUsers().stream().map(User::of).toList())
                .createdAt(roleDto.getCreatedAt())
                .updatedAt(roleDto.getUpdatedAt())
                .build();
    }

    public static Role of(UserRoleDto userRoleDto) {
        return Role.builder()
                .id(userRoleDto.getId())
                .name(userRoleDto.getName())
                .permissions(userRoleDto.getPermissions().stream().map(Permission::of).toList())
                .createdAt(userRoleDto.getCreatedAt())
                .updatedAt(userRoleDto.getUpdatedAt())
                .build();
    }

    public static Role of(PermissionRoleDto permissionRoleDto) {
        return Role.builder()
                .id(permissionRoleDto.getId())
                .name(permissionRoleDto.getName())
                .createdAt(permissionRoleDto.getCreatedAt())
                .updatedAt(permissionRoleDto.getUpdatedAt())
                .build();
    }
}
