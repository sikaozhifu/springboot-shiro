package com.example.springbootshiro.mapper;

import com.example.springbootshiro.entity.UserRoleRef;
import org.apache.ibatis.jdbc.SQL;

public class UserRoleRefSqlProvider {

    public String insertSelective(UserRoleRef record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("user_role_ref");
        
        if (record.getId() != null) {
            sql.VALUES("id", "#{id,jdbcType=INTEGER}");
        }
        
        if (record.getUserId() != null) {
            sql.VALUES("user_id", "#{userId,jdbcType=INTEGER}");
        }
        
        if (record.getRoleId() != null) {
            sql.VALUES("role_id", "#{roleId,jdbcType=INTEGER}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(UserRoleRef record) {
        SQL sql = new SQL();
        sql.UPDATE("user_role_ref");
        
        if (record.getUserId() != null) {
            sql.SET("user_id = #{userId,jdbcType=INTEGER}");
        }
        
        if (record.getRoleId() != null) {
            sql.SET("role_id = #{roleId,jdbcType=INTEGER}");
        }
        
        sql.WHERE("id = #{id,jdbcType=INTEGER}");
        
        return sql.toString();
    }
}