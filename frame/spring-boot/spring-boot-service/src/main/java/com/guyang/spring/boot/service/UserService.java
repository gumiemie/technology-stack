package com.guyang.spring.boot.service;

import com.guyang.spring.boot.model.User;

import java.util.List;

public interface UserService {
    List<User> getAll();

    void insert(User user);

    void update(User user);
}
