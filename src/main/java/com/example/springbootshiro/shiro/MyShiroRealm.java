package com.example.springbootshiro.shiro;

import cn.hutool.core.lang.Validator;
import com.example.springbootshiro.entity.Permission;
import com.example.springbootshiro.entity.Role;
import com.example.springbootshiro.entity.User;
import com.example.springbootshiro.service.PermissionService;
import com.example.springbootshiro.service.RoleService;
import com.example.springbootshiro.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Log4j2
public class MyShiroRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PermissionService permissionService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        log.info("进入授权【doGetAuthorizationInfo】");
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        User user = (User) principals.getPrimaryPrincipal();
        List<Role> roleList = roleService.getRoleListByUserId(user.getId());
        for (Role role : roleList) {
                //添加角色
                authorizationInfo.addRole(role.getRole());
                List<Permission> permissionList = permissionService.getPermissionListByRoleId(role.getId());
                for (Permission permission : permissionList) {
                    //添加权限
                    authorizationInfo.addStringPermission(permission.getPermission());
                }
        }
        return authorizationInfo;
    }


    /**
     * 登录认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        //加入这一步的目的是在post请求的时候会先认证，然后再请求
        if (authenticationToken.getPrincipal() == null){
            return null;
        }
        log.info("进入认证【doGetAuthenticationInfo】");
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String username = token.getUsername();
        User user = null;
        if (Validator.isEmail(username)){
            user = userService.getUserByEmail(username);
        }else {
            user = userService.getUserByUserName(username);
        }
        if (user == null){
            log.info("用户不存在！");
            return null;
        }
        //封装AuthenticationInfo，准备验证
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(
                user, user.getPassword(), ByteSource.Util.bytes(user.getSalt()), getName());
        return info;
    }

    /**
     * 重写方法,清除当前用户的的 授权缓存
     * @param principals
     */
    @Override
    public void clearCachedAuthorizationInfo(PrincipalCollection principals) {
        super.clearCachedAuthorizationInfo(principals);
    }

    /**
     * 重写方法，清除当前用户的 认证缓存
     * @param principals
     */
    @Override
    public void clearCachedAuthenticationInfo(PrincipalCollection principals) {
        super.clearCachedAuthenticationInfo(principals);
    }

    @Override
    public void clearCache(PrincipalCollection principals) {
        super.clearCache(principals);
    }

    /**
     * 自定义方法：清除所有 授权缓存
     */
    public void clearAllCachedAuthorizationInfo() {
        getAuthorizationCache().clear();
    }

    /**
     * 自定义方法：清除所有 认证缓存
     */
    public void clearAllCachedAuthenticationInfo() {
        getAuthenticationCache().clear();
    }

    /**
     * 自定义方法：清除所有的  认证缓存  和 授权缓存
     */
    public void clearAllCache() {
        clearAllCachedAuthenticationInfo();
        clearAllCachedAuthorizationInfo();
    }

    /**
     * 加密
     * @param args
     */
    public static void main(String[] args) {
        String hashAlgorithName = "MD5";
        String password = "123";
        int hashIterations = 1024;

        ByteSource credentialsSalt = ByteSource.Util.bytes("tom");
        Object obj = new SimpleHash(hashAlgorithName, password, credentialsSalt, hashIterations);
        log.info(obj);
    }
}
