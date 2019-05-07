package com.example.springbootshiro.service;

import com.example.springbootshiro.entity.Permission;

import java.util.List;

public interface PermissionService {
    Permission getPermissionById(Integer id);

    List<Permission> getPermissionListByRoleId(Integer roleId);
}
