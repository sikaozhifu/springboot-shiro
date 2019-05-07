package com.example.springbootshiro.service;

import com.example.springbootshiro.entity.User;

import java.util.List;

public interface UserService {

    List<User> getUser(User user);

    User getUserById(Integer id);

    User getUserByUserName(String username);

    User getUserByEmail(String email);
}
