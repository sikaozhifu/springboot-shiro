package com.example.springbootshiro.mapper;

import com.example.springbootshiro.entity.UserRoleRef;
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
public interface UserRoleRefMapper {
    @Delete({
        "delete from user_role_ref",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into user_role_ref (id, user_id, ",
        "role_id)",
        "values (#{id,jdbcType=INTEGER}, #{userId,jdbcType=INTEGER}, ",
        "#{roleId,jdbcType=INTEGER})"
    })
    int insert(UserRoleRef record);

    @InsertProvider(type=UserRoleRefSqlProvider.class, method="insertSelective")
    int insertSelective(UserRoleRef record);

    @Select({
        "select",
        "id, user_id, role_id",
        "from user_role_ref",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.INTEGER),
        @Result(column="role_id", property="roleId", jdbcType=JdbcType.INTEGER)
    })
    UserRoleRef selectByPrimaryKey(Integer id);

    @UpdateProvider(type=UserRoleRefSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(UserRoleRef record);

    @Update({
        "update user_role_ref",
        "set user_id = #{userId,jdbcType=INTEGER},",
          "role_id = #{roleId,jdbcType=INTEGER}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(UserRoleRef record);

    @Select({
            "select",
            "id, user_id, role_id",
            "from user_role_ref",
            "where user_id = #{user_id,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="user_id", property="userId", jdbcType=JdbcType.INTEGER),
            @Result(column="role_id", property="roleId", jdbcType=JdbcType.INTEGER)
    })
    List<UserRoleRef> getUserRoleRefByUserId(Integer user_id);
}