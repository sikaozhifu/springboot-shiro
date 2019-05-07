package com.example.springbootshiro.entity;

import lombok.Data;

@Data
public class RolePermissionRef {
    private Integer id;

    private Integer roleId;

    private Integer permissionId;

}