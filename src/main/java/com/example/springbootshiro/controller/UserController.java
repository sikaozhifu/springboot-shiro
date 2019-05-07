package com.example.springbootshiro.controller;

import com.example.springbootshiro.entity.User;
import com.example.springbootshiro.service.UserService;
import com.example.springbootshiro.utils.EncryptUtils;
import lombok.extern.log4j.Log4j2;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/user")
@Log4j2
public class UserController {

    @Autowired
    private UserService userService;
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login(
            @RequestParam("username")String username,
            @RequestParam("password")String password,
            @RequestParam(name = "rememberMe",required = false)boolean rememberMe,
            HttpServletRequest request){

        Subject subject = SecurityUtils.getSubject();
        User user = userService.getUserByUserName(username);
        if (user != null){
            UsernamePasswordToken token = new UsernamePasswordToken(username, EncryptUtils.encryptMD5(user.getSalt(), password),rememberMe);
            try {
                subject.login(token);
            }catch (UnknownAccountException e){
                //账号不存在
                log.info("账号不存在");
                request.setAttribute("login", "账号不存在");
                return "/login";
            }catch (IncorrectCredentialsException e){
                log.info("密码错误");
                request.setAttribute("login", "密码不正确");
                return "/login";
            }catch (Exception e){
                log.info(e.getMessage());
                return "/login";
            }
        }
        return "index";
    }

    @RequestMapping(value = "/logout",method = RequestMethod.GET)
    public String logout(){
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();
        subject.logout();
        log.info("用户[{}]退出登录" + user.getUsername());
        return "/login";
    }
}
