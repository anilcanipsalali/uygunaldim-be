package com.uygunaldim.service;

import com.uygunaldim.data.dto.PermissionDto;
import com.uygunaldim.data.dto.request.PermissionRequest;
import com.uygunaldim.data.entity.Permission;
import com.uygunaldim.data.entity.Role;
import com.uygunaldim.exception.AlreadyExistsException;
import com.uygunaldim.exception.NotFoundException;
import com.uygunaldim.repository.PermissionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static com.uygunaldim.util.ApplicationConstants.PERMISSION_BAD_REQUEST;
import static com.uygunaldim.util.ApplicationConstants.PERMISSION_NOT_FOUND;

@Service
@Slf4j
@RequiredArgsConstructor
public class PermissionService {

    private final PermissionRepository permissionRepository;

    public List<PermissionDto> getAllPermissions() {
        return permissionRepository.findAll().stream().map(PermissionDto::of).toList();
    }

    public PermissionDto getPermissionById(Long id) {
        return PermissionDto.of(findPermissionById(id));
    }

    protected Permission findPermissionById(Long id) {
        return permissionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(PERMISSION_NOT_FOUND, "Permission could not found by id: " + id));
    }

    public PermissionDto updatePermission(PermissionRequest request) {
        Permission permission = findPermissionById(request.getId());
        permission.setName(request.getName());
        permission.setDescription(request.getDescription());
        permission.setUpdatedAt(LocalDateTime.now());
        permission.setRole(Role.of(request.getRole()));
        return PermissionDto.of(permissionRepository.save(permission));
    }

    public PermissionDto createPermission(PermissionRequest request) {
        if (isPermissionExists(request)) {
            throw new AlreadyExistsException(PERMISSION_BAD_REQUEST, "Permission already exists with name: " + request.getName());
        }

        return PermissionDto.of(
                permissionRepository.save(Permission.builder()
                        .name(request.getName())
                        .description(request.getDescription())
                        .createdAt(LocalDateTime.now())
                        .updatedAt(LocalDateTime.now())
                        .role(Role.of(request.getRole()))
                        .build())
        );
    }

    public String deletePermission(Long id) {
        String name = findPermissionById(id).getName();
        permissionRepository.deleteById(id);
        return "Permission with name: " + name + " is deleted!";
    }

    private boolean isPermissionExists(PermissionRequest request) {
        return permissionRepository.existsByName(request.getName());
    }
}
