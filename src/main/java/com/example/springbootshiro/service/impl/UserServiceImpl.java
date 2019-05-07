package com.example.springbootshiro.service.impl;

import com.example.springbootshiro.entity.User;
import com.example.springbootshiro.mapper.UserMapper;
import com.example.springbootshiro.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> getUser(User user) {
        return userMapper.getUser(user);
    }

    @Override
    public User getUserById(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public User getUserByUserName(String username) {
        User user = new User();
        user.setUsername(username);
        List<User> list = userMapper.getUser(user);
        if (list.size() > 0){
            return list.get(0);
        }else {
            return null;
        }
    }

    @Override
    public User getUserByEmail(String email) {
        User user = new User();
        user.setEmail(email);
        return userMapper.getUser(user).get(0);
    }
}
