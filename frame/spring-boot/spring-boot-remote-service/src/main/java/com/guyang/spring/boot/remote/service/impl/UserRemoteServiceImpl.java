package com.guyang.spring.boot.remote.service.impl;

import com.guyang.spring.boot.model.User;
import com.guyang.spring.boot.remote.service.UserRemoteService;
import com.guyang.spring.boot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author guyang <guyang@ebnew.com>
 * @description
 * @date 2019-12-23 15:02
 */
//@Service(version = "1.0")
public class UserRemoteServiceImpl implements UserRemoteService {

    @Autowired
    private UserService userService;

    @Override
    public List<User> getAll() {
        return userService.getAll();
    }
}
