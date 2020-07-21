package com.guyang.spring.boot.service;

import com.guyang.spring.boot.model.User;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Validated
public interface UserService {
    List<User> getAll();

    void insert(User user);

    void update(User user);

    User findByPk(@Valid @NotBlank String id);
}
