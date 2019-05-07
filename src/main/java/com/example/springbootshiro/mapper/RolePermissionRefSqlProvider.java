package com.example.springbootshiro.mapper;

import com.example.springbootshiro.entity.RolePermissionRef;
import org.apache.ibatis.jdbc.SQL;

public class RolePermissionRefSqlProvider {

    public String insertSelective(RolePermissionRef record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("role_permission_ref");
        
        if (record.getId() != null) {
            sql.VALUES("id", "#{id,jdbcType=INTEGER}");
        }
        
        if (record.getRoleId() != null) {
            sql.VALUES("role_id", "#{roleId,jdbcType=INTEGER}");
        }
        
        if (record.getPermissionId() != null) {
            sql.VALUES("permission_id", "#{permissionId,jdbcType=INTEGER}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(RolePermissionRef record) {
        SQL sql = new SQL();
        sql.UPDATE("role_permission_ref");
        
        if (record.getRoleId() != null) {
            sql.SET("role_id = #{roleId,jdbcType=INTEGER}");
        }
        
        if (record.getPermissionId() != null) {
            sql.SET("permission_id = #{permissionId,jdbcType=INTEGER}");
        }
        
        sql.WHERE("id = #{id,jdbcType=INTEGER}");
        
        return sql.toString();
    }
}