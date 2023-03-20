package com.uygunaldim.data.entity;

import com.uygunaldim.data.dto.PermissionDto;
import com.uygunaldim.data.dto.RolePermissionDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "PERMISSION")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Permission {
    @Id
    @SequenceGenerator(name = "seqPermissionId", sequenceName = "seq_permission_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqPermissionId")
    private Long id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "CREATED_AT")
    private LocalDateTime createdAt;
    @Column(name = "UPDATED_AT")
    private LocalDateTime updatedAt;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    private Role role;

    public static Permission of(PermissionDto permissionDto) {
        return Permission.builder()
                .id(permissionDto.getId())
                .name(permissionDto.getName())
                .description(permissionDto.getDescription())
                .role(Role.of(permissionDto.getRole()))
                .createdAt(permissionDto.getCreatedAt())
                .updatedAt(permissionDto.getUpdatedAt())
                .build();
    }

    public static Permission of(RolePermissionDto rolePermissionDto) {
        return Permission.builder()
                .id(rolePermissionDto.getId())
                .name(rolePermissionDto.getName())
                .description(rolePermissionDto.getDescription())
                .createdAt(rolePermissionDto.getCreatedAt())
                .updatedAt(rolePermissionDto.getUpdatedAt())
                .build();
    }
}
