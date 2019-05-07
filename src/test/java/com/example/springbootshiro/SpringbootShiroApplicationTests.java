package com.example.springbootshiro;

import com.example.springbootshiro.entity.User;
import com.example.springbootshiro.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Log4j2
public class SpringbootShiroApplicationTests {

    @Autowired
    private UserService userService;
    @Test
    public void getUser() {
        User user = userService.getUserByUserName("tom");
//        User user = userService.getUserById(1);
        log.info(user);
    }

}
