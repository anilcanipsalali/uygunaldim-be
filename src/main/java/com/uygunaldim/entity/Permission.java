package com.uygunaldim.entity;

import com.uygunaldim.dto.PermissionDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "PERMISSION")
@AllArgsConstructor
@NoArgsConstructor
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
    @ManyToOne(cascade = CascadeType.ALL)
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
}
