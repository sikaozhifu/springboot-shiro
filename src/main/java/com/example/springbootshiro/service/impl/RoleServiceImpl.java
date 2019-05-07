package com.example.springbootshiro.service.impl;

import com.example.springbootshiro.entity.Role;
import com.example.springbootshiro.entity.UserRoleRef;
import com.example.springbootshiro.mapper.RoleMapper;
import com.example.springbootshiro.mapper.UserRoleRefMapper;
import com.example.springbootshiro.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private UserRoleRefMapper userRoleRefMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<Role> getRoleListByUserId(Integer userId) {
        List<Role> roleList = new ArrayList<>();
        List<UserRoleRef> userRoleRefList = userRoleRefMapper.getUserRoleRefByUserId(userId);
        for (UserRoleRef userRoleRef : userRoleRefList) {
            Role role = roleMapper.selectByPrimaryKey(userRoleRef.getRoleId());
            roleList.add(role);
        }
        return roleList;
    }

    @Override
    public Role getRoleByRoleName(String role) {
        return roleMapper.getRoleByRoleName(role);
    }

    @Override
    public List<Role> getAllRole() {
        return roleMapper.getAllRole();
    }

    @Override
    public Role getRoleById(Integer id) {
        return roleMapper.selectByPrimaryKey(id);
    }
}
