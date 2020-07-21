package com.guyang.spring.boot.app.test.service;

import com.guyang.spring.boot.app.test.common.BaseTestApplication;
import com.guyang.spring.boot.remote.service.UserRemoteService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

@AutoConfigureMockMvc
public class UserServiceImplTest extends BaseTestApplication {

    @Autowired
    private UserRemoteService userService;

    @Test
    public void getAll() {
        System.out.println(userService.getAll(null));
    }

    @Test
    public void insert() {
        //userService.insert(new User());
    }

    @Test
    public void update() {
    }

    @Test
    public void setApplicationEventPublisher() {
    }

    @Test
    public void findByPk(){
      //  User byPk = userService.findByPk("");
    }
}