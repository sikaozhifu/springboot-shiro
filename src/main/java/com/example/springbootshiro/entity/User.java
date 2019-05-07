package com.example.springbootshiro.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class User implements Serializable {
    private Integer id;

    private String username;

    private String password;

    private String name;

    private String email;

    private String salt;

    private Date createTime;

    private Date updateTime;

}