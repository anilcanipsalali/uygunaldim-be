package com.uygunaldim.repository;

import com.uygunaldim.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long> {
    Boolean existsByName(String name);
}
