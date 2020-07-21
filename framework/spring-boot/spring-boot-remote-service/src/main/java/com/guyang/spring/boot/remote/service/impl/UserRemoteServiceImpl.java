package com.guyang.spring.boot.remote.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.guyang.spring.boot.model.User;
import com.guyang.spring.boot.remote.service.UserRemoteService;
import com.guyang.spring.boot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * @author guyang <guyang@ebnew.com>
 * @description
 * @date 2019-12-23 15:02
 */
@Service(version = "1.0")
@Validated
public class UserRemoteServiceImpl implements UserRemoteService {

    @Autowired
    private UserService userService;

    @Override
    public List<User> getAll(Integer type) {
        return null;
    }
}
