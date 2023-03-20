package com.uygunaldim.controller;

import com.uygunaldim.data.dto.PermissionDto;
import com.uygunaldim.data.dto.request.PermissionRequest;
import com.uygunaldim.service.PermissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/permission")
@RequiredArgsConstructor
public class PermissionController {
    private final PermissionService permissionService;

    @GetMapping
    public ResponseEntity<List<PermissionDto>> getAllPermissions() {
        return ResponseEntity.ok(permissionService.getAllPermissions());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PermissionDto> getPermissionById(@PathVariable Long id) {
        return ResponseEntity.ok(permissionService.getPermissionById(id));
    }

    @PostMapping("/create")
    public ResponseEntity<PermissionDto> createPermission(@Valid @RequestBody PermissionRequest request) {
        return ResponseEntity.ok(permissionService.createPermission(request));
    }

    @PostMapping("/update")
    public ResponseEntity<PermissionDto> updatePermission(@Valid @RequestBody PermissionRequest request) {
        return ResponseEntity.ok(permissionService.updatePermission(request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePermission(@PathVariable Long id) {
        return ResponseEntity.ok(permissionService.deletePermission(id));
    }
}
