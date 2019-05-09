package com.example.springbootshiro.service;

import com.example.springbootshiro.entity.Permission;
import com.example.springbootshiro.entity.RolePermissionRef;

import java.util.List;

public interface PermissionService {
    Permission getPermissionById(Integer id);

    List<Permission> getPermissionListByRoleId(Integer roleId);

    int insertRolePermissionRef(RolePermissionRef rolePermissionRef);
}
