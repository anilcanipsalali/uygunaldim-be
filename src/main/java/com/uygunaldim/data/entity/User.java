package com.uygunaldim.data.entity;

import com.uygunaldim.data.dto.RoleUserDto;
import com.uygunaldim.data.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "USERS")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class User {
    @Id
    @SequenceGenerator(name = "seqUsersId", sequenceName = "seq_users_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqUsersId")
    private Long id;
    @Column(name = "EMAIL", nullable = false, unique = true)
    private String email;
    @Column(name = "USERNAME", nullable = false, unique = true)
    private String username;
    @Column(name = "PASSWORD", nullable = false)
    private String password;
    @Column(name = "CREATED_AT")
    private LocalDateTime createdAt;
    @Column(name = "UPDATED_AT")
    private LocalDateTime updatedAt;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    private Role role;

    public static User of(UserDto userDto) {
        return User.builder()
                .id(userDto.getId())
                .email(userDto.getEmail())
                .username(userDto.getUsername())
                .password(userDto.getPassword())
                .role(Role.of(userDto.getRole()))
                .createdAt(userDto.getCreatedAt())
                .updatedAt(userDto.getUpdatedAt())
                .build();
    }

    public static User of(RoleUserDto roleUserDto) {
        return User.builder()
                .id(roleUserDto.getId())
                .email(roleUserDto.getEmail())
                .username(roleUserDto.getUsername())
                .createdAt(roleUserDto.getCreatedAt())
                .updatedAt(roleUserDto.getUpdatedAt())
                .build();
    }
}
