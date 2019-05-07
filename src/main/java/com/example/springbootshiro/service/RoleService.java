package com.example.springbootshiro.service;

import com.example.springbootshiro.entity.Role;

import java.util.List;

public interface RoleService {

    List<Role> getRoleListByUserId(Integer userId);

    Role getRoleByRoleName(String role);

    List<Role> getAllRole();

    Role getRoleById(Integer id);
}
