package com.guyang.spring.boot.action.controller;

import com.guyang.spring.boot.core.dto.RespDto;
import com.guyang.spring.boot.model.User;
import com.guyang.spring.boot.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author guyang <guyang@ebnew.com>
 * @description
 * @date 2019-11-13 15:21
 */
@RestController
@RequestMapping("user/json")
@Validated
public class UserAction {

    @Value("${spring-datasource-driver-class-name}")
    private String diverClassName;

    private UserService userService;

    @RequestMapping("/getAll")
    public List<User> getAll(@Valid @NotNull(message = "type不能为空！") Integer type) {
        return userService.getAll();
    }

    @RequestMapping("/save")
    public RespDto save(@Valid User user) {
        if (user.getId() == null) {
            userService.insert(user);
        } else {
            userService.update(user);
        }
        return new RespDto();
    }

}
