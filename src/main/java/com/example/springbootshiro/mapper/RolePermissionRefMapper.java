package com.example.springbootshiro.mapper;

import com.example.springbootshiro.entity.RolePermissionRef;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RolePermissionRefMapper {
    @Delete({
        "delete from role_permission_ref",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into role_permission_ref (id, role_id, ",
        "permission_id)",
        "values (#{id,jdbcType=INTEGER}, #{roleId,jdbcType=INTEGER}, ",
        "#{permissionId,jdbcType=INTEGER})"
    })
    int insert(RolePermissionRef record);

    @InsertProvider(type=RolePermissionRefSqlProvider.class, method="insertSelective")
    int insertSelective(RolePermissionRef record);

    @Select({
        "select",
        "id, role_id, permission_id",
        "from role_permission_ref",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="role_id", property="roleId", jdbcType=JdbcType.INTEGER),
        @Result(column="permission_id", property="permissionId", jdbcType=JdbcType.INTEGER)
    })
    RolePermissionRef selectByPrimaryKey(Integer id);

    @UpdateProvider(type=RolePermissionRefSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(RolePermissionRef record);

    @Update({
        "update role_permission_ref",
        "set role_id = #{roleId,jdbcType=INTEGER},",
          "permission_id = #{permissionId,jdbcType=INTEGER}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(RolePermissionRef record);


    @Select({
            "select",
            "id, role_id, permission_id",
            "from role_permission_ref",
            "where role_id = #{role_id,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="role_id", property="roleId", jdbcType=JdbcType.INTEGER),
            @Result(column="permission_id", property="permissionId", jdbcType=JdbcType.INTEGER)
    })
    List<RolePermissionRef> getRolePermissionRefByRoleId(Integer role_id);
}