package com.guyang.spring.boot.service.impl;

import com.guyang.spring.boot.dao.UserDao;
import com.guyang.spring.boot.model.User;
import com.guyang.spring.boot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author guyang <guyang@ebnew.com>
 * @description
 * @date 2019-11-14 17:16
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public List<User> getAll() {
        return userDao.getAll();
    }
}
