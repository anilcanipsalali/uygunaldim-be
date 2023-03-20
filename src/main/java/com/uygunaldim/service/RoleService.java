package com.uygunaldim.service;

import com.uygunaldim.data.dto.RoleDto;
import com.uygunaldim.data.dto.request.RoleRequest;
import com.uygunaldim.data.entity.Permission;
import com.uygunaldim.data.entity.Role;
import com.uygunaldim.data.entity.User;
import com.uygunaldim.exception.AlreadyExistsException;
import com.uygunaldim.exception.NotFoundException;
import com.uygunaldim.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static com.uygunaldim.util.ApplicationConstants.ROLE_BAD_REQUEST;

@Service
@Slf4j
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;
    private final PermissionService permissionService;

    public List<RoleDto> getAllRoles() {
        return roleRepository.findAll().stream().map(RoleDto::of).toList();
    }

    public RoleDto getRoleById(Long id) {
        return RoleDto.of(findRoleById(id));
    }

    protected Role findRoleById(Long id) {
        return roleRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ROLE_BAD_REQUEST, "Role could not found by id: " + id));
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
        if (isRoleExists(request.getName())) {
            throw new AlreadyExistsException(ROLE_BAD_REQUEST, "Role already exists with name: " + request.getName());
        }

        return RoleDto.of(
                roleRepository.save(Role.builder()
                        .name(request.getName())
                        .permissions(Collections.emptyList())
                        .users(Collections.emptyList())
                        .createdAt(LocalDateTime.now())
                        .updatedAt(LocalDateTime.now())
                        .build())
        );
    }

    public RoleDto getRoleOrCreateIfNotExists(RoleRequest request) {
        if (isRoleExists(request.getName())) {
           return RoleDto.of(roleRepository.findByName(request.getName()));
        } else {
            return createRole(request);
        }
    }

    public String deleteRole(Long id) {
        String name = findRoleById(id).getName();
        roleRepository.deleteById(id);
        return "Role with name: " + name + " is deleted!";
    }

    private boolean isRoleExists(String name) {
        return roleRepository.existsByName(name);
    }
}
