package com.uygunaldim.service;

import com.uygunaldim.dto.RoleDto;
import com.uygunaldim.dto.request.RoleRequest;
import com.uygunaldim.entity.Permission;
import com.uygunaldim.entity.Role;
import com.uygunaldim.entity.User;
import com.uygunaldim.exception.AlreadyExistsException;
import com.uygunaldim.exception.NotFoundException;
import com.uygunaldim.repository.RoleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class RoleService {
    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public List<RoleDto> getAllRoles() {
        return roleRepository.findAll().stream().map(RoleDto::of).toList();
    }

    public RoleDto getRoleById(Long id) {
        return RoleDto.of(findRoleById(id));
    }

    protected Role findRoleById(Long id) {
        return roleRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("UYGNALDM-ROLE-404", "Role could not found by id: " + id));
    }

    public RoleDto updateRole(RoleRequest request) {
        Role role = findRoleById(request.getId());
        role.setName(request.getName());
        role.setPermissions(request.getPermissions().stream().map(Permission::of).toList());
        role.setUsers(request.getUsers().stream().map(User::of).toList());
        role.setUpdatedAt(LocalDateTime.now());
        return RoleDto.of(roleRepository.save(role));
    }

    public RoleDto createRole(RoleRequest request) {
        if (isRoleExists(request)) {
            throw new AlreadyExistsException("UYGNALDM-ROLE-400", "Role already exists with name: " + request.getName());
        }

        return RoleDto.of(
                roleRepository.save(Role.builder()
                        .name(request.getName())
                        .permissions(request.getPermissions().stream().map(Permission::of).toList())
                        .users(request.getUsers().stream().map(User::of).toList())
                        .createdAt(LocalDateTime.now())
                        .updatedAt(LocalDateTime.now())
                        .build())
        );
    }

    public String deleteRole(Long id) {
        Role role = findRoleById(id);
        roleRepository.deleteById(id);
        return "Role with name: " + role.getName() + " is deleted!";
    }

    private boolean isRoleExists(RoleRequest request) {
        return roleRepository.existsByName(request.getName());
    }
}
