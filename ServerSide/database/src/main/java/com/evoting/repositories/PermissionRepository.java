package com.evoting.repositories;

import com.evoting.domain.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Azhar on 2016/07/14.
 */

public interface PermissionRepository extends JpaRepository<Permission, Integer>
{
    Permission findById(int id);
    Permission getPermissionByPermission(String permission);
}