package com.example.springbootshiro.service.impl;

import com.example.springbootshiro.entity.Permission;
import com.example.springbootshiro.entity.RolePermissionRef;
import com.example.springbootshiro.mapper.PermissionMapper;
import com.example.springbootshiro.mapper.RolePermissionRefMapper;
import com.example.springbootshiro.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionMapper permissionMapper;

    @Autowired
    private RolePermissionRefMapper rolePermissionRefMapper;

    @Override
    public Permission getPermissionById(Integer id) {
        return permissionMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Permission> getPermissionListByRoleId(Integer roleId) {
        List<Permission> permissionList = new ArrayList<>();
        List<RolePermissionRef> rolePermissionRefList = rolePermissionRefMapper.getRolePermissionRefByRoleId(roleId);
        for (RolePermissionRef rolePermissionRef : rolePermissionRefList) {
            Permission permission = permissionMapper.selectByPrimaryKey(rolePermissionRef.getPermissionId());
            permissionList.add(permission);
        }
        return permissionList;
    }

    @Override
    public int insertRolePermissionRef(RolePermissionRef rolePermissionRef) {
        return rolePermissionRefMapper.insert(rolePermissionRef);
    }
}
